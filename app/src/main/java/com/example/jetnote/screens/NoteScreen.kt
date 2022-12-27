package com.example.jetnote.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.CustomButton
import com.example.jetnote.components.CustomListTile
import com.example.jetnote.components.CustomTextField
import com.example.jetnote.roomDatabase.NoteModelEntity
import com.example.jetnote.view_model.NoteViewModel
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<NoteModelEntity>,
    onAddNote: (NoteModelEntity) -> Unit,
    onRemoveNote: (NoteModelEntity) -> Unit,
    onEditNote: (NoteModelEntity) -> Unit,

    ) {
    // HANDLE VARIABLE
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    var isEditNote by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current


    // COMPOSE WIDGET
    Column(
        modifier = Modifier.padding(6.dp)
    ) {
        // APPBAR
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notification Icon"
            )
        }, backgroundColor = Color(0xFFDADFE3)
        )

        // CONTENT BODY
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // NOTE TEXT FIELD
            // Title
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                })

            // Desc
            CustomTextField(modifier = Modifier.fillMaxWidth(),
                text = description,
                label = "Description",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            Spacer(modifier = Modifier.height(16.dp))

            // BUTTON
            CustomButton(
                title = if (isEditNote) "Edit Note" else "Add Note",
                modifier = Modifier.fillMaxWidth(),
                onButtonTap = {
                    if (isEditNote) {
                        if (title.isNotEmpty() && description.isNotEmpty()) {
                            // Edit Note into List
                            if (title.isNotEmpty() && description.isNotEmpty()) {
                                onEditNote(NoteModelEntity(title = title, description = description))
                            }
                            // CLear variable
                            title = ""
                            description = ""
                            isEditNote = false
                            Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        if (title.isNotEmpty() && description.isNotEmpty()) {
                            // Save Note into List
                            if (title.isNotEmpty() && description.isNotEmpty()) {
                                onAddNote(NoteModelEntity(title = title, description = description))
                            }
                            // CLear variable
                            title = ""
                            description = ""
                            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

            // Divider
            Divider(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            )

            // Lazy Column
            LazyColumn(content = {
                items(notes) { note ->
                    CustomListTile(note = note, onDeleteButton = { onRemoveNote(it) }, onTapCard = {
                        title = note.title
                        description = note.description
                        isEditNote = true
                    })
                }
            })

        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteScreenPreview() {
//    NoteScreen(notes = emptyList(), onAddNote = { /*TODO*/ }, onRemoveNote = {})
}