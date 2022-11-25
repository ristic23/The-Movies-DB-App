package com.example.repository_remote

import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.detailMovie.DetailsMovie

interface IRepositoryMovies {

    suspend fun getTopRatedMovies(): Movies

    suspend fun getMovieDetails(moviesId: Int): DetailsMovie

    suspend fun searchMovies()

    suspend fun getSimilarMovies(moviesId: Int)


}