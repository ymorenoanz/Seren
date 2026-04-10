package com.ymorenoanz.seren.ui.states

import com.ymorenoanz.seren.domain.model.DayPeriod
import com.ymorenoanz.seren.domain.model.MoodEntry
import com.ymorenoanz.seren.domain.model.MoodType

data class MoodUIState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val moods: List<MoodEntry> = emptyList(),
    val error: String? = null,
    val selectedMood: MoodType? = null,
    val note: String = "",
    val selectedPeriod: DayPeriod? = null,
    val moodError: String? = null,
    val periodError: String? = null,
    val noteError: String? = null,
    val userName: String? = ""
)