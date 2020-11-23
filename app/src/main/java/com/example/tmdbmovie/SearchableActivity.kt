package com.example.tmdbmovie

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SearchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            Log.i("MYTAG: ", query.toString())
        }
    }
}