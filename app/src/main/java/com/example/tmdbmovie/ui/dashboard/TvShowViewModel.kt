package com.example.tmdbmovie.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TvShowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tv show Fragment"
    }
    val text: LiveData<String> = _text
}