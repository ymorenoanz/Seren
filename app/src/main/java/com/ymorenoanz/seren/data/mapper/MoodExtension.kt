package com.ymorenoanz.seren.data.mapper

import com.ymorenoanz.seren.domain.model.MoodType

fun MoodType.toEmoji(): String {
    return when (this) {
        MoodType.VERY_SAD -> "😢"
        MoodType.SAD -> "🙁"
        MoodType.NEUTRAL -> "😐"
        MoodType.HAPPY -> "🙂"
        MoodType.VERY_HAPPY -> "😄"

    }
}