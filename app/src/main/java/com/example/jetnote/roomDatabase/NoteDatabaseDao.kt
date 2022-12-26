package com.example.jetnote.roomDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * from note_table")
    fun getAllNotes(): Flow<List<NoteModelEntity>>

    @Query("SELECT * from note_table where id=:id")
    suspend fun getNoteById(id: String): NoteModelEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModelEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: NoteModelEntity)

    @Query("DELETE from note_table")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note: NoteModelEntity)

}