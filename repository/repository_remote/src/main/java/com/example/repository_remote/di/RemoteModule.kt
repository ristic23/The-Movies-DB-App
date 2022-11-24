package com.example.repository_remote.di

import com.example.repository_remote.IRepositoryMovies
import com.example.repository_remote.RepositoryMovies
import com.example.retrofit.RetrofitController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRepositoryMoviesInterface(
        retrofitController: RetrofitController
    ): IRepositoryMovies = RepositoryMovies(retrofitController)

}