package com.example.movies_list_presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dtoMovies.Movies
import com.example.repository_remote.IRepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies
): ViewModel()
{
    private val moviesResult = MutableLiveData<Movies>()
    val moviesResultLiveData: LiveData<Movies> get() = moviesResult

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val topRated = repositoryMovies.getTopRatedMovies()
            moviesResult.postValue(topRated)
        }
    }

}