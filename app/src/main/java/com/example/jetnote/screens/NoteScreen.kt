package com.example.jetnote.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.R
import com.example.jetnote.components.CustomButton
import com.example.jetnote.components.CustomListTile
import com.example.jetnote.components.CustomTextField
import com.example.jetnote.model.NoteModel
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<NoteModel>,
    onAddNote: () -> Unit,
    onRemoveNote: () -> Unit
) {
    // HANDLE VARIABLE
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }


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
            CustomTextField(modifier = Modifier.fillMaxWidth(),
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
            CustomButton(title = "Add Note", modifier = Modifier.fillMaxWidth(), onButtonTap = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    // Save Note into List


                    // CLear variable
                    title = ""
                    description = ""
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
                    CustomListTile(
                        title = note.title,
                        description = note.description,
                        timeStamp = note.date.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy")),
                        onDeleteButton = {}
                    )
                }
            })


        }


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = emptyList(), onAddNote = { /*TODO*/ }, onRemoveNote = {})
}