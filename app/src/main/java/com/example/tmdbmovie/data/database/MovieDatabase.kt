package com.example.tmdbmovie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tmdbmovie.data.model.movie.Movie

@TypeConverters(Converters::class)
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract val movieDao : MovieDao

}