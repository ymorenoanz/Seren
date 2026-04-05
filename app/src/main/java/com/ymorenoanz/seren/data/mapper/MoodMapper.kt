package com.ymorenoanz.seren.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.ymorenoanz.seren.data.local.entity.MoodEntity
import com.ymorenoanz.seren.domain.model.DayPeriod
import com.ymorenoanz.seren.domain.model.MoodEntry
import com.ymorenoanz.seren.domain.model.MoodType
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
fun MoodEntity.toDomain(): MoodEntry {
    return MoodEntry(
        id = id,
        date = LocalDate.parse(date),
        period = DayPeriod.valueOf(period),
        mood = MoodType.valueOf(mood),
        note = note
    )
}

fun MoodEntry.toEntity(): MoodEntity {
    return MoodEntity(
        id = id,
        date = date.toString(),
        period = period?.name ?: "",
        mood = mood?.name ?: "",
        note = note
    )
}