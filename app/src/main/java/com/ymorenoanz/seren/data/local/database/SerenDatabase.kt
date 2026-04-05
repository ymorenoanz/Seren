package com.ymorenoanz.seren.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ymorenoanz.seren.data.local.dao.MoodDao
import com.ymorenoanz.seren.data.local.entity.MoodEntity

@Database(
    entities = [MoodEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SerenDatabase : RoomDatabase() {
    abstract fun moodDao(): MoodDao
}