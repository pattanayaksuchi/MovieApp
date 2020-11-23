package com.example.tmdbmovie.data.model.movie

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

@Parcelize
@Entity(tableName = "movies_table")
data class Movie(

    @PrimaryKey
    val id: Int,

    val adult: Boolean,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    val overview: String,

    val popularity: Double,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    val title: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int,

    @ColumnInfo(name = "last_updated_at")
    var lastUpdatedAt: String

) : Parcelable