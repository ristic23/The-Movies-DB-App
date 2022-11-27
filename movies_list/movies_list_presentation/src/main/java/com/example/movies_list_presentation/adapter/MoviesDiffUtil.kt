package com.example.movies_list_presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.dtoMovies.Movie

class MoviesDiffUtil(
): DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        (oldItem.id == newItem.id)
}