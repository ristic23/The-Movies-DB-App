package com.example.movies_list_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.core.dtoMovies.Movie
import com.example.movies_list_presentation.databinding.LinearVerticalMovieBinding
import com.example.retrofit.util.IMAGE_BASE_URL

class MoviesAdapter(
//    val myDataSet: MutableList<Movie>,
    val movieOnClick: (Int) -> Unit
): PagingDataAdapter<Movie, MoviesAdapter.MovieItemViewHolder>(MoviesDiffUtil())
//    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            itemBinding = (LinearVerticalMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )),
            movieOnClick = {
                movieOnClick(it)
            }
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    class MovieItemViewHolder(
        val itemBinding: LinearVerticalMovieBinding,
        val movieOnClick: (Int) -> Unit
    ): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movie: Movie) {
            itemBinding.movieTitle.text = movie.title
            itemBinding.moviePopularity.text = "(${movie.popularity})"
            itemBinding.movieVote.text = movie.vote_average.toString()
            itemBinding.movieDesc.text = movie.overview
            Glide.with(itemBinding.movieImage.context).load(IMAGE_BASE_URL + movie.poster_path).thumbnail(0.1f)
                .transition(withCrossFade()).into(itemBinding.movieImage)

            itemBinding.movieRoot.setOnClickListener {
                movieOnClick(movie.id)
            }


        }

    }

}