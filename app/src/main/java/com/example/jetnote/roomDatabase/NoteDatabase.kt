package com.example.jetnote.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteModelEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDatabaseDao

}