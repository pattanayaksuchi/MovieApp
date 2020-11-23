package com.example.tmdbmovie.ui.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbmovie.R
import com.example.tmdbmovie.data.model.movie.Movie

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val completePath = "https://image.tmdb.org/t/p/w200/$imgUrl"
        val imgUri = completePath.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context).load(imgUri)
            .placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image).into(imageView)
    }

}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}