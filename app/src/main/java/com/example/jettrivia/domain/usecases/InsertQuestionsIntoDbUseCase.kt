package com.example.jettrivia.domain.usecases

import com.example.jettrivia.domain.entities.QuestionItem
import com.example.jettrivia.domain.repository.QuestionRepository
import javax.inject.Inject

class InsertQuestionsIntoDbUseCase @Inject constructor(private val repository: QuestionRepository) {
    suspend fun insertQuestionsIntoDb(questions: List<QuestionItem>) {
        repository.insertQuestionsToDb(questions)
    }
}