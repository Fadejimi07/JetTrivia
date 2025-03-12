package com.example.jettrivia.domain.usecases

import com.example.jettrivia.domain.entities.QuestionItem
import com.example.jettrivia.domain.repository.QuestionRepository
import com.example.jettrivia.domain.resource.DataError
import com.example.jettrivia.domain.resource.DataResult
import javax.inject.Inject

class LoadQuestionsFromNetworkUseCase @Inject constructor(val questionRepository: QuestionRepository) {
    suspend fun getAllQuestionsFromNetwork(): DataResult<List<QuestionItem>, DataError> {
        return questionRepository.getAllQuestionsFromNetwork()
    }
}