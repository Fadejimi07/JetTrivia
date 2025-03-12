package com.example.jettrivia.data.services.network

import com.example.jettrivia.domain.entities.QuestionItem
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiService {
    @GET("world.json")
    suspend fun getAllQuestions(): Response<List<QuestionItem>>
}