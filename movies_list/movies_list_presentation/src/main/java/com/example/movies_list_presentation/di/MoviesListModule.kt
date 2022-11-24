package com.example.movies_list_presentation.di

import com.example.movies_list_presentation.viewModel.MoviesListViewModelFactory
import com.example.movies_list_presentation.viewModel.MoviesListViewModelFactoryImpl
import com.example.repository_remote.IRepositoryMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesListModule {

//    @Singleton
//    @Provides
//    fun provideMoviesListViewModel(
//        repositoryMovies: IRepositoryMovies
//    ): MoviesListViewModelFactory = MoviesListViewModelFactoryImpl(repositoryMovies)


}