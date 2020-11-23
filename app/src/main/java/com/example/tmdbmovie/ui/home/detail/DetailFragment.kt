package com.example.tmdbmovie.ui.home.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.tmdbmovie.R
import com.example.tmdbmovie.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : DetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)

        binding.movie = args.movie

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel
    }



}