package com.example.movies_details_presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dtoMovies.castAndCrew.MovieCast
import com.example.core.dtoMovies.castAndCrew.MovieCastAndCrew
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core.dtoMovies.favorite.MovieFavorite
import com.example.core.dtoMovies.recommendations.RecommendationMovies
import com.example.repository_remote.IRepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies

) : ViewModel()
{
    private val movieResult = MutableLiveData<DetailsMovie>()
    val movieResultLiveData: LiveData<DetailsMovie> get() = movieResult

    private val movieCastAndCrew = MutableStateFlow(MovieCastAndCrew())
    val movieCastAndCrewFlow: StateFlow<MovieCastAndCrew> get() = movieCastAndCrew

    private val recommendedMovies = MutableStateFlow(RecommendationMovies())
    val recommendedMoviesFlow: StateFlow<RecommendationMovies> get() = recommendedMovies

    private val  isMovieInFavorites = MutableStateFlow(false)
    val isMovieInFavoritesFlow: StateFlow<Boolean> get() = isMovieInFavorites

    private var isMovieInFavoritesLastState = false
    private var isMovieInFavoritesOnCreateState = false
    private var favorite: MovieFavorite? = MovieFavorite(0,0)

    private var movieId = 0

    fun getMovie() {
        viewModelScope.launch {
            val movie = repositoryMovies.getMovieDetails(movieId)
            val tempIsAdded = repositoryMovies.isFavoritesAdded(movieId)
            favorite = repositoryMovies.getFavoritesSpecific(movieId)
            movie.isAddedInFavorites = tempIsAdded
            isMovieInFavoritesLastState = tempIsAdded
            isMovieInFavoritesOnCreateState = tempIsAdded
            movieResult.postValue(movie)
            isMovieInFavorites.value = (tempIsAdded)
            getCastAndCrew(movieId)
            getMovieRecommendations(movieId)
        }
    }

    private fun getCastAndCrew(moviesId: Int) {
        viewModelScope.launch {
            val castAndCrew = repositoryMovies.getMovieCastCrew(moviesId)
            movieCastAndCrew.value = castAndCrew
        }
    }

    private fun getMovieRecommendations(moviesId: Int) {
        viewModelScope.launch {
            val movies = repositoryMovies.getMovieRecommendations(moviesId)
            recommendedMovies.value = movies
        }
    }

    fun updateMovieId(movieId: Int) {
        this.movieId = movieId
    }
    fun getUpdateMovieId() = movieId

    fun updateFavoritesClick(state: Boolean) {
        isMovieInFavorites.value = state
        isMovieInFavoritesLastState = state
    }

    fun updateMovieInFavorites() {
        viewModelScope.launch {
            if(isMovieInFavoritesOnCreateState != isMovieInFavoritesLastState) {
                if(isMovieInFavoritesLastState)
                    repositoryMovies.addMovieToFavorites(MovieFavorite(movie_id = movieId))
                else
                    if(favorite != null)
                        repositoryMovies.removeMovieFromFavorites(favorite!!)
            }
        }
    }

}