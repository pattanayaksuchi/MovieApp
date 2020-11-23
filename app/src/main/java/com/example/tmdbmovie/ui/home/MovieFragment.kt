package com.example.tmdbmovie.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.tmdbmovie.R
import com.example.tmdbmovie.databinding.FragmentMovieBinding
import com.example.tmdbmovie.ui.adapters.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var binding: FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = movieViewModel

        binding.movieList.adapter = MovieAdapter(MovieAdapter.OnMovieClickListener {
            Log.i("MYTAG: ", "Movie release year is ${it.releaseDate}")
            val action = MovieFragmentDirections.actionNavigationHomeToDetailFragment(it)
            findNavController().navigate(action)
        })

        binding.swipeToRefresh.setOnRefreshListener {
            Log.i("MYTAG: ", "Swiped. Please refresh now.")
            updateMovies(true)
        }

        movieViewModel.movieList.observe(viewLifecycleOwner, Observer {
            if (it == null || it.isEmpty()) return@Observer
            Log.i("MYTAG: ", "Searched Results are $it")
            movieViewModel.resetProgressBar()
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.onActionViewExpanded()
        searchView.requestFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("MYTAG: ", "Submitted Query is $query")
                movieViewModel.searchMovie(query)
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("MYTAG: ", "Updated Query is $newText")

                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updateMovies(refresh: Boolean) {
        movieViewModel.getAllMovies(refresh)
        binding.swipeToRefresh.isRefreshing = false
    }

}