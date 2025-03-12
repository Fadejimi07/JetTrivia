package com.example.jettrivia.di

import android.content.Context
import androidx.room.Room
import com.example.jettrivia.TriviaDb
import com.example.jettrivia.data.services.db.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideTriviaDb(@ApplicationContext context: Context): TriviaDb =
        Room.databaseBuilder(
            context = context,
            TriviaDb::class.java,
            "trivia_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideQuestionDao(triviaDb: TriviaDb): QuestionDao = triviaDb.questionDao()
}