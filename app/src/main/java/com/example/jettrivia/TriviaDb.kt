package com.example.jettrivia

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jettrivia.data.services.db.QuestionDao
import com.example.jettrivia.domain.entities.QuestionItem

@Database(entities = [QuestionItem::class], version = 1)
abstract class TriviaDb : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}