package com.example.seren.ui.states

import com.example.seren.domain.model.DayPeriod
import com.example.seren.domain.model.MoodEntry
import com.example.seren.domain.model.MoodType

data class MoodUIState(
    val isLoading: Boolean = false,
    val moods: List<MoodEntry> = emptyList(),
    val error: String? = null,
    val selectedMood: MoodType? = null,
    val note: String = "",
    val selectedPeriod: DayPeriod? = null,
    val moodError: String? = null,
    val periodError: String? = null,
    val noteError: String? = null
)