package com.example.tmdbmovie.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tmdbmovie.data.database.MovieDao
import com.example.tmdbmovie.data.model.Resource
import com.example.tmdbmovie.data.model.movie.Movie
import com.example.tmdbmovie.data.netwok.MovieList
import com.example.tmdbmovie.data.netwok.TMDBService
import com.example.tmdbmovie.data.netwok.asModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.time.ZonedDateTime
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val tmdbService: TMDBService,
    private val movieDao: MovieDao
) {

    @ExperimentalCoroutinesApi
    fun getAllMovies(manualRefresh: Boolean = false): Flow<Resource<List<Movie>>> {

        return object : NetworkBoundResource<List<Movie>, MovieList>() {

            override suspend fun saveToDb(items: MovieList) = withContext(Dispatchers.IO) {
                val dbFormatted = items.asModel()
                dbFormatted.map {
                    it.lastUpdatedAt = ZonedDateTime.now().toString()
                }
                movieDao.insertMovies(dbFormatted)
            }

            override fun loadFromDb(): Flow<List<Movie>> = movieDao.getMovies()

            override suspend fun callApi(): Response<MovieList> = tmdbService.getMovies()
            override fun shouldFetch(data: List<Movie>): Boolean {
                if (data.isNotEmpty()) {
                    val lastUpdatedDate = ZonedDateTime.parse(data[0].lastUpdatedAt)
                    Log.i("MYTAG: ", "Last Updated at $lastUpdatedDate")
                    // TO-DO: Change the api refresh interval to 5
                    val fiveMinutesAgo = ZonedDateTime.now().minusMinutes(300)
                    Log.i("MYTAG: ", "Five Minutes Ago is $fiveMinutesAgo")
                    Log.i("MYTAG: ", "Api call made is ${lastUpdatedDate.isBefore(fiveMinutesAgo)}")
                    return lastUpdatedDate.isBefore(fiveMinutesAgo) || manualRefresh
                }
                return true
            }


        }.asFlow()
    }

    suspend fun getSearchedMovies(searchQuery: String): List<Movie> {
        val apiResponse = tmdbService.getSearchedMovies(searchQuery)
        val movieList = apiResponse.body()?.asModel()
        if (apiResponse.isSuccessful && !movieList.isNullOrEmpty()) {
            return movieList
        }
        return emptyList()
    }

}