package com.example.movies_favorites_presentation.di

import com.example.movies_favorites_presentation.viewModel.MoviesFavoritesViewModelFactory
import com.example.movies_favorites_presentation.viewModel.MoviesFavoritesViewModelFactoryImpl
import com.example.repository_remote.IRepositoryMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {

    @Singleton
    @Provides
    fun provideMoviesFavoritesViewModel(
        repositoryMovies: IRepositoryMovies
    ): MoviesFavoritesViewModelFactory = MoviesFavoritesViewModelFactoryImpl(repositoryMovies)

}