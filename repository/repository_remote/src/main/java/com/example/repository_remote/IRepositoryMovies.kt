package com.example.repository_remote

import com.example.retrofit.dto.RetrofitMovies
interface IRepositoryMovies {

    suspend fun getTopRatedMovies(): List<RetrofitMovies>

    suspend fun getMoviesDetails(moviesId: Int)

    suspend fun searchMovies()

    suspend fun getSimilarMovies(moviesId: Int)


}