package com.example.jettrivia.domain.repository

import com.example.jettrivia.data.services.db.QuestionDao
import com.example.jettrivia.data.services.network.ApiService
import com.example.jettrivia.domain.entities.QuestionItem
import com.example.jettrivia.domain.resource.DataError
import com.example.jettrivia.domain.resource.DataResult
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val questionDao: QuestionDao,
    private val apiService: ApiService,
) : QuestionRepository {
    override suspend fun getAllQuestionsFromNetwork(): DataResult<List<QuestionItem>, DataError> {
        val response = apiService.getAllQuestions()

        return when {
            response.isSuccessful -> {
                DataResult.Success(response.body() as List<QuestionItem>)
            }

            else -> {
                val error = response.code()
                when (error) {
                    408 -> DataResult.Error(DataError.Network.REQUEST_TIMEOUT)
                    401 -> DataResult.Error(DataError.Network.TOO_MANY_REQUESTS)
                    else -> DataResult.Error(DataError.Network.NO_INTERNET)
                }
            }
        }
    }

    override suspend fun getAllQuestionsFromDb(): DataResult<List<QuestionItem>, DataError> {
        return try {
            DataResult.Success(questionDao.getAllQuestions())
        } catch (e: Exception) {
            DataResult.Error(DataError.Local.DISK_FREE)
        }
    }

    override suspend fun insertQuestionsToDb(questions: List<QuestionItem>) {
        questionDao.insertQuestionList(questions)
    }
}