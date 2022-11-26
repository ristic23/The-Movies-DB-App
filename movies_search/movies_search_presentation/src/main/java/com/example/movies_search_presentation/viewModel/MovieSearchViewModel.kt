package com.example.movies_search_presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.favorite.MovieFavorite
import com.example.repository_remote.IRepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies
): ViewModel() {

    private var currentQuery = ""

    private val moviesResult = MutableLiveData<Movies>()
    val moviesResultLiveData: LiveData<Movies> get() = moviesResult

    fun updateCurrentQuery(newQuery: String) {
        currentQuery = newQuery
    }

    fun startSearch(query: String?) {
        viewModelScope.launch {
            if(currentQuery != "")
            {
                val searchResult = repositoryMovies.searchMovies(currentQuery)
                moviesResult.postValue(searchResult)
            }
        }
    }

}