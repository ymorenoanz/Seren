package com.example.seren.di

import android.content.Context
import androidx.room.Room
import com.example.seren.data.local.dao.MoodDao
import com.example.seren.data.local.database.SerenDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SerenDatabase {
        return Room.databaseBuilder(
            context,
            SerenDatabase::class.java,
            "seren_db"
        ).build()
    }

    @Provides
    fun provideMoodDao(database: SerenDatabase): MoodDao {
        return database.moodDao()
    }
}
