package com.example.movies_list_presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_list_presentation.databinding.LinearVerticalMovieBinding
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.core.dtoMovies.Movie
import com.example.retrofit.util.BASE_URL
import com.example.retrofit.util.IMAGE_BASE_URL

class MoviesAdapter(
    val myDataSet: MutableList<Movie>,
    val movieOnClick: (Int) -> Unit,
    val addToFavoritesOnClick: () -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(newDataSet: List<Movie>) {
        myDataSet.clear()
        myDataSet.addAll(newDataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieItemViewHolder(
            itemBinding = (LinearVerticalMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )),
            movieOnClick = {
                movieOnClick(it)
            },
            addToFavoritesOnClick = {
                addToFavoritesOnClick()
            }
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MovieItemViewHolder
        viewHolder.bind(myDataSet[position])
    }

    override fun getItemCount(): Int = myDataSet.size

    class MovieItemViewHolder(
        val itemBinding: LinearVerticalMovieBinding,
        val movieOnClick: (Int) -> Unit,
        val addToFavoritesOnClick: () -> Unit
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