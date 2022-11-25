package com.example.retrofit

import com.example.retrofit.dto.RetrofitMovie
import com.example.retrofit.dto.RetrofitMovies
import com.example.retrofit.dto.detailMovie.RetrofitDetailsMovie
import com.example.retrofit.util.BASE_URL
import com.example.retrofit.util.MOVIES_API_KEY
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.OkHttpClient
import java.lang.reflect.Type
import javax.inject.Inject

class RetrofitController @Inject constructor(
    private val iRetrofit: IRetrofit
)
{

    suspend fun getTopRatedMovies(): RetrofitMovies {
        return  try {
            val response = iRetrofit.getTopRatedMovies(
                "${BASE_URL}top_rated?api_key=$MOVIES_API_KEY&language=en-US&page=1"
            )
            if(response.isSuccessful)
            {
                val moshi: Moshi = Moshi.Builder().build()
                val type: Type = Types.newParameterizedType(RetrofitMovies::class.java, RetrofitMovies::class.java)
                val jsonAdapter: JsonAdapter<RetrofitMovies> = moshi.adapter(type)
                response.body()?.let {
                    val responseString = it.string()
                    val finalResult: RetrofitMovies = jsonAdapter.fromJson(responseString) ?: RetrofitMovies()
                    return finalResult
                }
                RetrofitMovies()
            }
            else
            {
                RetrofitMovies()
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            RetrofitMovies()
        }
    }

    suspend fun getMoviesDetails(moviesId: Int): RetrofitDetailsMovie {
        return  try {
            val response = iRetrofit.getMoviesDetails(
                "${BASE_URL}$moviesId?api_key=$MOVIES_API_KEY&language=en-US"
            )
            if(response.isSuccessful)
            {
                val moshi: Moshi = Moshi.Builder().build()
                val type: Type = Types.newParameterizedType(RetrofitDetailsMovie::class.java, RetrofitDetailsMovie::class.java)
                val jsonAdapter: JsonAdapter<RetrofitDetailsMovie> = moshi.adapter(type)
                response.body()?.let {
                    val responseString = it.string()
                    val finalResult: RetrofitDetailsMovie = jsonAdapter.fromJson(responseString) ?: emptyRetrofitDetailMovie()
                    return finalResult
                }
                emptyRetrofitDetailMovie()
            }
            else
            {
                emptyRetrofitDetailMovie()
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            emptyRetrofitDetailMovie()
        }
    }

    private fun emptyRetrofitMovie(): RetrofitMovie = RetrofitMovie(
        false,
        "",
        listOf(),
        0,
        "",
        "",
        "",
        0.0,
        "",
        "",
        "",
        false,
        0.0,
        0
    )
    private fun emptyRetrofitDetailMovie(): RetrofitDetailsMovie = RetrofitDetailsMovie(
        "",
        0,
        listOf(),
        0,
        "",
        "",
        "",
        0.0,
        "",
        "",
        0,
        0,
        "",
        "",
        "",
        0.0,
        0
    )


    suspend fun searchMovies(query: String): List<RetrofitMovies> {
        val result = iRetrofit.searchMovies(query =  query)
        return listOf()
    }

    suspend fun getSimilarMovies(moviesId: Int): List<RetrofitMovies> {
        val result = iRetrofit.getSimilarMovies(moviesId = moviesId)
        return listOf()
    }


}