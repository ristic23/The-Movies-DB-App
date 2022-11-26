package com.example.movies_favorites_presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.repository_remote.IRepositoryMovies


interface MoviesFavoritesViewModelFactory : ViewModelProvider.Factory
class MoviesFavoritesViewModelFactoryImpl(
    private val repositoryMovies: IRepositoryMovies
)
    : MoviesFavoritesViewModelFactory
{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviesFavoritesViewModel(repositoryMovies) as T
    }

}