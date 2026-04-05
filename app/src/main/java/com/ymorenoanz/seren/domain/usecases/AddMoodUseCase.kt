package com.ymorenoanz.seren.domain.usecases

import com.ymorenoanz.seren.domain.model.MoodEntry
import com.ymorenoanz.seren.domain.repository.MoodRepository
import javax.inject.Inject

class AddMoodUseCase @Inject constructor
    (private val repository: MoodRepository) {

    //Add mood
    suspend operator fun invoke(moodEntry: MoodEntry){
        repository.insertMood(moodEntry)
    }
}