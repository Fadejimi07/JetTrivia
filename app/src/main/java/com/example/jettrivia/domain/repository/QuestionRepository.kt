package com.example.jettrivia.domain.repository

import com.example.jettrivia.domain.entities.QuestionItem
import com.example.jettrivia.domain.resource.DataError
import com.example.jettrivia.domain.resource.DataResult

interface QuestionRepository {
    suspend fun getAllQuestionsFromNetwork(): DataResult<List<QuestionItem>, DataError>
    suspend fun getAllQuestionsFromDb(): DataResult<List<QuestionItem>, DataError>
    suspend fun insertQuestionsToDb(questions: List<QuestionItem>)
}