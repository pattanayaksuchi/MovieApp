package com.example.tmdbmovie.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbmovie.R

class ArtistsFragment : Fragment() {

    private lateinit var artistsViewModel: ArtistsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        artistsViewModel =
                ViewModelProvider(this).get(ArtistsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        artistsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}