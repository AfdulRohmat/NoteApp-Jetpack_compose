package com.example.jetnote.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.repository.NoteRepository
import com.example.jetnote.roomDatabase.NoteModelEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    private val _noteList = MutableStateFlow<List<NoteModelEntity>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        getAllNote()
    }

    // GET ALL NOTE
    private fun getAllNote() = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.getALlNotes().distinctUntilChanged().collect { listOfNote ->
            if (listOfNote.isEmpty()) {
                Log.d("Empty List", "Empty List")
            } else {
                _noteList.value = listOfNote
            }

        }
    }


    // GET NOTE BY ID
    suspend fun getNoteById(id: String) = viewModelScope.launch {
        noteRepository.getNoteById(id)
    }

    // ADD NOTE
    suspend fun addNote(note: NoteModelEntity) = viewModelScope.launch {
        noteRepository.addNote(note)
    }

    // UPDATE NOTE
    suspend fun updateNote(note: NoteModelEntity) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    // DELETE NOTE
    suspend fun deleteNote(note: NoteModelEntity) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    // DELETE ALL NOTE
    suspend fun deleteAllNote() = viewModelScope.launch {
        noteRepository.deleteAllNote()
    }


}