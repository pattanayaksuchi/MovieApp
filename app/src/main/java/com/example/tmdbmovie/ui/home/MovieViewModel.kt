package com.example.tmdbmovie.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.tmdbmovie.data.model.Resource
import com.example.tmdbmovie.data.model.movie.Movie
import com.example.tmdbmovie.data.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieViewModel @ViewModelInject constructor (private val repository: MovieRepository) : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()

    private val _loadingBar = MutableLiveData<Boolean>().apply {
        value = true
    }
    val loadingBar : LiveData<Boolean> = _loadingBar

    init {
        getAllMovies()
    }

    fun searchMovie(query: String?) {
        _loadingBar.value = true
        viewModelScope.launch {
            movieList.value = repository.getSearchedMovies(query!!)
        }
    }

    fun resetProgressBar() {
        _loadingBar.value = false
    }

    fun getAllMovies(refresh: Boolean = false) {
        viewModelScope.launch {
            repository.getAllMovies(refresh).collect {
                when (it) {
                    is Resource.Success -> {
                        movieList.value = it.data
                        _loadingBar.value = false
                    }
                    is Resource.Error -> Log.i("MYTAG: ", "Error in repository ${it.message}")
                    is Resource.Loading -> _loadingBar.value = true
                }
            }
        }
    }

}