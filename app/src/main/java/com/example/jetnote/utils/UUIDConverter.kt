package com.example.jetnote.utils

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun uuidFromString(uuid: UUID): String {
        return  uuid.toString()
    }

    @TypeConverter
    fun stringFromUUID(id: String) : UUID? {
        return UUID.fromString(id)
    }
}