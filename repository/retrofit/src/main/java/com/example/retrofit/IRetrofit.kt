package com.example.retrofit


import com.example.retrofit.dto.RetrofitMovies
import com.example.retrofit.util.MOVIES_API_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IRetrofit {

    @GET("/top_rated?api_key={api_key}&language=en-US&page=1")
    suspend fun getTopRatedMovies(
        @Path("api_key") api_key: String = MOVIES_API_KEY
    ): Response<List<RetrofitMovies>>

    @GET("/{movie_id}?api_key={api_key}&language=en-US")
    suspend fun getMoviesDetails(
        @Path("api_key") api_key: String = MOVIES_API_KEY,
        @Path("movie_id") moviesId: Int
    ): Response<ResponseBody>

    @GET("https://api.themoviedb.org/3/search/movie?api_key={api_key}&language=en-US&page=1&include_adult=false")
    suspend fun searchMovies(
        @Path("api_key") api_key: String = MOVIES_API_KEY,
        @Path("query") query: String
    ): Response<ResponseBody>

    @GET("/{movie_id}/similar?api_key={api_key}&language=en-US&page=1")
    suspend fun getSimilarMovies(
        @Path("api_key") api_key: String = MOVIES_API_KEY,
        @Path("movie_id") moviesId: Int
    ): Response<ResponseBody>

}