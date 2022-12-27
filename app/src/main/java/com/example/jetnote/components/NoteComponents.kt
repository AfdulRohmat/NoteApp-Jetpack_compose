package com.example.jetnote.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetnote.roomDatabase.NoteModelEntity
import com.example.jetnote.utils.formatDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    modifier: Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = maxLine,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        })
    )

}

@Composable
fun CustomButton(
    title: String,
    modifier: Modifier,
    onButtonTap: () -> Unit = {},
) {
    Button(
        onClick = onButtonTap,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00AA13)),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        Text(text = title, color = Color.White)

    }
}

@Composable
fun CustomListTile(
    note: NoteModelEntity, onDeleteButton: (NoteModelEntity) -> Unit, onTapCard: () -> Unit
) {
    Card(
        elevation = 8.dp, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onTapCard() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(0.8f)
            ) {
                Text(text = note.title, modifier = Modifier.padding(top = 4.dp))
                Text(text = note.description, modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = formatDate(note.date.time),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { onDeleteButton(note) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")

                }

            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun NoteComponentsPreview() {

}