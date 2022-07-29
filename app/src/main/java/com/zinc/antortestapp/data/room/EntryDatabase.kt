package com.zinc.antortestapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zinc.antortestapp.data.model.EntryDto

@Database(entities = [EntryDto::class], version = 1, exportSchema = false)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun entryDao(): EntryDao

    companion object {
        const val DATABASE_NAME = "entry_db"
    }
}