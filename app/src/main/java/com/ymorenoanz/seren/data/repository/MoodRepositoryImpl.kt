package com.ymorenoanz.seren.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.ymorenoanz.seren.data.local.dao.MoodDao
import com.ymorenoanz.seren.data.mapper.toDomain
import com.ymorenoanz.seren.data.mapper.toEntity
import com.ymorenoanz.seren.domain.model.MoodEntry
import com.ymorenoanz.seren.domain.repository.MoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoodRepositoryImpl @Inject constructor(private val
                                             moodDao: MoodDao): MoodRepository {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun getAllMoods(): Flow<List<MoodEntry>> {
            return moodDao.getAllMoods()
                .map { entities ->
                    entities.map { it.toDomain() }
                }
        }

        override suspend fun insertMood(mood: MoodEntry) {
            moodDao.insertMood(mood.toEntity())
        }
}