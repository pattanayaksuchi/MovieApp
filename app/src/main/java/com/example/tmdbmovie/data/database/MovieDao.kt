package com.example.tmdbmovie.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbmovie.data.model.movie.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_table")
    fun getMovies() : Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

}