package com.example.repository_remote

import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.repository_remote.mapper.retrofitMovieDetailToMovieDetail
import com.example.repository_remote.mapper.retrofitMoviesToMovies
import com.example.retrofit.RetrofitController
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private val retrofitController: RetrofitController
): IRepositoryMovies
{
    override suspend fun getTopRatedMovies(): Movies  {
        val result = retrofitController.getTopRatedMovies()
        return retrofitMoviesToMovies(result)
    }

    override suspend fun getMovieDetails(moviesId: Int): DetailsMovie {
        val result = retrofitController.getMoviesDetails(moviesId)
        return retrofitMovieDetailToMovieDetail(result)
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getSimilarMovies(moviesId: Int) {
        TODO("Not yet implemented")
    }

}