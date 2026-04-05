package com.example.seren.domain.usecases

import com.example.seren.domain.model.MoodEntry
import com.example.seren.domain.repository.MoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoodEntriesUseCase @Inject constructor(
    private val repository: MoodRepository
) {
    //Read and update list
    operator fun invoke(): Flow<List<MoodEntry>> {
        return repository.getAllMoods()
    }
}