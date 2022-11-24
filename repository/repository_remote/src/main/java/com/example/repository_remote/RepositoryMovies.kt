package com.example.repository_remote

import com.example.retrofit.RetrofitController
import com.example.retrofit.dto.RetrofitMovies
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private val retrofitController: RetrofitController
): IRepositoryMovies
{
    override suspend fun getTopRatedMovies(): List<RetrofitMovies>  {
        val result = retrofitController.getTopRatedMovies()
        return result
    }

    override suspend fun getMoviesDetails(moviesId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarMovies(moviesId: Int) {
        TODO("Not yet implemented")
    }

}