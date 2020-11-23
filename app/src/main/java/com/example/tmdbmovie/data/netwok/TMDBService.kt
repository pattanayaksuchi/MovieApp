package com.example.tmdbmovie.data.netwok

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("3/movie/popular")
    suspend fun getMovies() : Response<MovieList>

    @GET("3/search/movie")
    suspend fun getSearchedMovies(@Query("query") searchedString: String) : Response<MovieList>

}