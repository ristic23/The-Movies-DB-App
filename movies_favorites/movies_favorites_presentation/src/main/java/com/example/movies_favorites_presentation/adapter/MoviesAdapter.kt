package com.example.movies_favorites_presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.movies_favorites_presentation.databinding.ItemFavoriteMovieBinding
import com.example.retrofit.util.IMAGE_BASE_URL

class MoviesAdapter(
    val myDataSet: MutableList<DetailsMovie>,
    val movieOnClick: (Int) -> Unit,
    val addToFavoritesOnClick: (Boolean, Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(newDataSet: List<DetailsMovie>) {
        myDataSet.clear()
        myDataSet.addAll(newDataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieItemViewHolder(
            itemBinding = (ItemFavoriteMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )),
            movieOnClick = {
                movieOnClick(it)
            },
            addToFavoritesOnClick = {state, id ->
                addToFavoritesOnClick(state, id)
            }
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MovieItemViewHolder
        viewHolder.bind(myDataSet[position])
    }

    override fun getItemCount(): Int = myDataSet.size

    class MovieItemViewHolder(
        val itemBinding: ItemFavoriteMovieBinding,
        val movieOnClick: (Int) -> Unit,
        val addToFavoritesOnClick: (Boolean, Int) -> Unit
    ): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movie: DetailsMovie) {
            itemBinding.movieTitle.text = movie.title
            itemBinding.moviePopularity.text = "(${movie.popularity})"
            itemBinding.movieVote.text = movie.vote_average.toString()
            itemBinding.movieDesc.text = movie.overview
            Glide.with(itemBinding.movieImage.context).load(IMAGE_BASE_URL + movie.poster_path).thumbnail(0.1f)
                .transition(withCrossFade()).into(itemBinding.movieImage)

            itemBinding.linearGridBtn.isChecked = movie.isAddedInFavorites
            itemBinding.linearGridBtn.setOnClickListener {
                if(movie.isAddedInFavorites)
                {
                    movie.isAddedInFavorites = false
                    addToFavoritesOnClick(false, movie.id)
                }
            }

            itemBinding.movieRoot.setOnClickListener {
                movieOnClick(movie.id)
            }


        }

    }

}