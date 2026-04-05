package com.ymorenoanz.seren.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "mood_entries",
    indices = [
        Index(value = ["date", "period"], unique = true)
    ]
)
data class MoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: String,
    val period: String,
    val mood: String,
    val note: String?
)