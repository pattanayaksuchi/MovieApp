package com.example.tmdbmovie.di

import android.content.Context
import androidx.room.Room
import com.example.tmdbmovie.data.database.MovieDao
import com.example.tmdbmovie.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movies").build()

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) : MovieDao = movieDatabase.movieDao

}