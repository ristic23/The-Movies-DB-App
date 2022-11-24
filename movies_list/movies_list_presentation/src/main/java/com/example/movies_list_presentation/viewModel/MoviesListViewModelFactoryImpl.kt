package com.example.movies_list_presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.repository_remote.IRepositoryMovies


interface MoviesListViewModelFactory : ViewModelProvider.Factory
class MoviesListViewModelFactoryImpl(
    private val repositoryMovies: IRepositoryMovies
)
    : MoviesListViewModelFactory
{

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MoviesListViewModel(repositoryMovies) as T
//    }

}