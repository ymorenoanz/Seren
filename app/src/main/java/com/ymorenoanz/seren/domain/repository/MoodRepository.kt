package com.example.seren.domain.repository

import com.example.seren.domain.model.MoodEntry
import kotlinx.coroutines.flow.Flow

interface MoodRepository {
        fun getAllMoods(): Flow<List<MoodEntry>>

        suspend fun insertMood(mood: MoodEntry)
}