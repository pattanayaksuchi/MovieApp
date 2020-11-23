package com.example.tmdbmovie.data.netwok


import com.google.gson.annotations.SerializedName

data class MovieList(
    val page: Int,
    @SerializedName("results")
    val networkMovies: List<NetworkMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)