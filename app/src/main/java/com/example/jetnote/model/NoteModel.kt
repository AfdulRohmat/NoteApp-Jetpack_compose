package com.example.jetnote.model

import java.time.LocalDateTime
import java.util.UUID

data class NoteModel(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val date: LocalDateTime = LocalDateTime.now()

)
