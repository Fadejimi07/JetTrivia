package com.example.jettrivia.domain.usecases

import com.example.jettrivia.domain.entities.QuestionItem
import com.example.jettrivia.domain.repository.QuestionRepository
import com.example.jettrivia.domain.resource.DataError
import com.example.jettrivia.domain.resource.DataResult
import javax.inject.Inject

class LoadQuestionsFromDbUseCase @Inject constructor(private val repository: QuestionRepository) {
    suspend fun getAllQuestionsFromDb(): DataResult<List<QuestionItem>, DataError> {
        return repository.getAllQuestionsFromDb()
    }
}