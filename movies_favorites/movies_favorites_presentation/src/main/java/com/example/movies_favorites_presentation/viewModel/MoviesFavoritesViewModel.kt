package com.example.movies_favorites_presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core.dtoMovies.favorite.MovieFavorite
import com.example.repository_remote.IRepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesFavoritesViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies

): ViewModel() {

    private val moviesResult = MutableLiveData<List<DetailsMovie>>(listOf())
    val moviesResultLiveData: LiveData<List<DetailsMovie>> get() = moviesResult

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            repositoryMovies.getAllFavorites().collectLatest {
                val favoriteList = mutableListOf<DetailsMovie>()
                it.forEach{ movieFavorite ->
                    val movie = repositoryMovies.getMovieDetails(movieFavorite.movie_id)
                    movie.isAddedInFavorites = true
                    favoriteList.add(movie)
                }
                moviesResult.postValue(favoriteList)
            }
        }
    }

    fun updateFavoritesClick(state: Boolean, movieId: Int) {
        viewModelScope.launch {
            val favorite = repositoryMovies.getFavoritesSpecific(movieId)
            if(favorite != null)
                repositoryMovies.removeMovieFromFavorites(favorite!!)
        }
    }

}