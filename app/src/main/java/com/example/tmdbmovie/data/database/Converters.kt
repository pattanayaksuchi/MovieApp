package com.example.tmdbmovie.data.database

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

class Converters {
    @TypeConverter
    fun fromZonedDateTime(dateTime: ZonedDateTime?): String? {
        return dateTime.toString()
    }

    @TypeConverter
    fun toZonedDateTime(string: String?): ZonedDateTime? {
        if (string == null) return ZonedDateTime.now()
        return ZonedDateTime.parse(string)
    }
}