package com.example.tmdbmovie.data.netwok

import com.example.tmdbmovie.data.model.movie.Movie
import java.util.*


fun MovieList.asModel(): List<Movie> {

    return networkMovies.map {
//        lateinit var path : String
//        if (it.posterPath.isNullOrEmpty()) path = "null"
//        else path = it.posterPath
        Movie(
            it.id,
            it.adult,
            it.originalLanguage,
            it.overview,
            it.popularity,
            it.posterPath,
            it.releaseDate,
            it.title,
            it.voteAverage,
            it.voteCount,
            "null"
        )
    }

}
