package com.ymorenoanz.seren.domain.model

import java.time.LocalDate

data class MoodEntry(
    val id: Long,
    val date: LocalDate,
    val period: DayPeriod?,
    val mood: MoodType?,
    val note: String?
)
