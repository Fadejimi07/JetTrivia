package com.example.jettrivia.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val category: String,
    val answer: String,
    val choices: List<String>
)