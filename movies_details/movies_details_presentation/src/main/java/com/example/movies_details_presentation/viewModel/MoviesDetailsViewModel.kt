package com.example.movies_details_presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.repository_remote.IRepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies

) : ViewModel()
{
    private val movieResult = MutableLiveData<DetailsMovie>()
    val movieResultLiveData: LiveData<DetailsMovie> get() = movieResult

    fun getMovie(moviesId: Int) {
        viewModelScope.launch {
            val topRated = repositoryMovies.getMovieDetails(moviesId)
            movieResult.postValue(topRated)
        }
    }
}