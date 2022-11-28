package com.example.movies_list_presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.core.dtoMovies.Movie
import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.castAndCrew.MovieCastAndCrew
import com.example.repository_remote.IRepositoryMovies
import com.example.repository_remote.mapper.retrofitMovieEntityToMovie
import com.example.roomdb.entities.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies
): ViewModel()
{
//    private val moviesResult = MutableLiveData<Movies>()
//    val moviesResultLiveData: LiveData<Movies> get() = moviesResult

//    private val moviesResultMutableFlow = MutableStateFlow<PagingData<Movie>>()
//    val moviesResultFlow: StateFlow<PagingData<Movie>> get() = moviesResultMutableFlow


//    fun getMovies()
//    {
//        viewModelScope.launch {
////            val topRated = repositoryMovies.getTopRatedMovies(1)
////            moviesResult.postValue(topRated)
//
//            repositoryMovies.getTopRatedMoviesPaging().collectLatest {
//                moviesResultMutableFlow.value = it.map { movieEntity ->
//                    retrofitMovieEntityToMovie(movieEntity)
//                }
//            }
//        }
//    }

    fun getMovies(): Flow<PagingData<Movie>> = repositoryMovies.getTopRatedMoviesPaging().cachedIn(viewModelScope)

}