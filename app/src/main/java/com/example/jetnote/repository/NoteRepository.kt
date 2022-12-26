package com.example.jetnote.repository

import com.example.jetnote.roomDatabase.NoteDatabaseDao
import com.example.jetnote.roomDatabase.NoteModelEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    // GET ALL NOTE
    fun getALlNotes(): Flow<List<NoteModelEntity>> =
        noteDatabaseDao.getAllNotes().flowOn(Dispatchers.IO).conflate()

    // GET NOTE BY ID
    suspend fun getNoteById(id: String) = noteDatabaseDao.getNoteById(id)

    // ADD NOTE
    suspend fun addNote(noteModelEntity: NoteModelEntity) =
        noteDatabaseDao.insertNote(noteModelEntity)

    // UPDATE NOTE
    suspend fun updateNote(noteModelEntity: NoteModelEntity) =
        noteDatabaseDao.updateNote(noteModelEntity)

    // DELETE NOTE
    suspend fun deleteNote(noteModelEntity: NoteModelEntity) =
        noteDatabaseDao.deleteNote(noteModelEntity)

    // DELETE ALL NOTE
    suspend fun deleteAllNote() = noteDatabaseDao.deleteAllNotes()

}