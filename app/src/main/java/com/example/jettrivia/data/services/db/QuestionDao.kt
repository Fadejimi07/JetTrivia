package com.example.jettrivia.data.services.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jettrivia.domain.entities.QuestionItem

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question_table")
    suspend fun getAllQuestions(): List<QuestionItem>

    @Insert
    suspend fun insertQuestionList(questions: List<QuestionItem>)
}