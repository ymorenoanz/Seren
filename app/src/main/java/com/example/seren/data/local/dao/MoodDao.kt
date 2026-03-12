package com.example.seren.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.seren.data.local.entity.MoodEntity
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {

    @Insert
    suspend fun insertMood(mood: MoodEntity)

    @Query("SELECT * FROM mood_entries ORDER BY date DESC")
    fun getAllMoods(): Flow<List<MoodEntity>>
}