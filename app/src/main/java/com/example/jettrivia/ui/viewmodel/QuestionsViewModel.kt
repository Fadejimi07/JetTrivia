package com.example.jettrivia.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jettrivia.domain.entities.QuestionItem
import com.example.jettrivia.domain.resource.DataError
import com.example.jettrivia.domain.resource.DataResult
import com.example.jettrivia.domain.usecases.InsertQuestionsIntoDbUseCase
import com.example.jettrivia.domain.usecases.LoadQuestionsFromDbUseCase
import com.example.jettrivia.domain.usecases.LoadQuestionsFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val insertQuestionsIntoDbUseCase: InsertQuestionsIntoDbUseCase,
    private val loadQuestionsFromDbUseCase: LoadQuestionsFromDbUseCase,
    private val loadQuestionsFromNetworkUseCase: LoadQuestionsFromNetworkUseCase
) : ViewModel() {
    var questionState: MutableState<UIState> = mutableStateOf(UIState.Loading)
        private set

    init {
        getAllQuestionsFromDb()
    }

    private fun getAllQuestionsFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            questionState.value = UIState.Loading
            when (val response = loadQuestionsFromDbUseCase.getAllQuestionsFromDb()) {
                is DataResult.Success -> {
                    if (response.data.isEmpty()) {
                        getAllQuestionsFromNetwork()
                    } else {
                        questionState.value = UIState.Success(response.data)
                    }
                }

                is DataResult.Error -> {
                    if (response.error == DataError.Local.DISK_FREE) {
                        questionState.value = UIState.Error("Disk is too full")
                    } else {
                        getAllQuestionsFromNetwork()
                    }
                }
            }
        }
    }

    private fun getAllQuestionsFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = loadQuestionsFromNetworkUseCase.getAllQuestionsFromNetwork()) {
                is DataResult.Success -> {
                    questionState.value = UIState.Success(response.data)
                    uploadQuestionsToDb(response.data)
                }

                is DataResult.Error -> {
                    questionState.value = UIState.Error("Network error message")
                }
            }
        }
    }

    private fun uploadQuestionsToDb(questions: List<QuestionItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertQuestionsIntoDbUseCase.insertQuestionsIntoDb(questions)
        }
    }
}

sealed class UIState {
    object Loading : UIState()
    data class Success(val data: Any) : UIState()
    data class Error(val errorMessage: String) : UIState()
}