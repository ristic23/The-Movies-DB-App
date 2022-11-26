package com.example.retrofit

import com.example.retrofit.util.MOVIES_API_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IRetrofit {

    @GET
    suspend fun getTopRatedMovies(
        @Url endPointUrl: String
    ): Response<ResponseBody>

    @GET
    suspend fun getMoviesDetails(
        @Url endPointUrl: String
    ): Response<ResponseBody>


    @GET
    suspend fun getMovieCredits(
        @Url endPointUrl: String
    ): Response<ResponseBody>


    @GET
    suspend fun getMovieRecommendations(
        @Url endPointUrl: String
    ): Response<ResponseBody>

    @GET
    suspend fun searchMovies(
        @Url endPointUrl: String
    ): Response<ResponseBody>


}