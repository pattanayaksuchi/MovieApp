package com.example.tmdbmovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbmovie.data.model.movie.Movie
import com.example.tmdbmovie.databinding.ItemViewBinding

class MovieAdapter(private val clickListener: OnMovieClickListener) : ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ViewHolder(private var binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    class OnMovieClickListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemViewBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(movie)
        }
        holder.bind(movie)
    }

}