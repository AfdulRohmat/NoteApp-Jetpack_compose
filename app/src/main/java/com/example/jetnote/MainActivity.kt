package com.example.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetnote.data.DummyNoteData
import com.example.jetnote.screens.NoteScreen
import com.example.jetnote.ui.theme.JetNoteTheme
import com.example.jetnote.view_model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels()

                    val noteList = noteViewModel.noteList.collectAsState().value

                    NoteScreen(
                        notes = noteList,
                        onAddNote = { noteViewModel.addNote(it) },
                        onRemoveNote = { noteViewModel.deleteNote(it) },
                        onEditNote = { noteViewModel.updateNote(it) },
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetNoteTheme {
        val listOfNote = DummyNoteData()

//        NoteScreen(
//            notes = listOfNote.loadAllDummyData(),
//            onAddNote = {},
//            onRemoveNote = {})
    }
}