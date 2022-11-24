package com.example.movies_list_presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.repository_remote.IRepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies
): ViewModel()
{

    fun getMovies() {
        viewModelScope.launch {

            val response = repositoryMovies.getTopRatedMovies()
            val response2 = repositoryMovies.getTopRatedMovies()
        }
    }

}