package com.example.retrofit

import com.example.retrofit.dto.RetrofitMovie
import com.example.retrofit.dto.RetrofitMovies
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

    suspend fun getTopRatedMovies(): List<RetrofitMovies> {
        return  try {
            val response = iRetrofit.getTopRatedMovies()
            if(response.isSuccessful)
            {
//                val moshi: Moshi = Moshi.Builder().build()
//                val type: Type = Types.newParameterizedType(MutableList::class.java, RetrofitMovies::class.java)
//                val jsonAdapter: JsonAdapter<RetrofitMovies> = moshi.adapter(type)
                response.body()?.let {
                   return it
                }

                listOf()
            }
            else
            {
                listOf()
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            listOf()
        }

    }

    suspend fun getMoviesDetails(moviesId: Int): RetrofitMovie {
        val result = iRetrofit.getMoviesDetails(moviesId = moviesId)
        return RetrofitMovie(
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
    }

    suspend fun searchMovies(query: String): List<RetrofitMovies> {
        val result = iRetrofit.searchMovies(query =  query)
        return listOf()
    }

    suspend fun getSimilarMovies(moviesId: Int): List<RetrofitMovies> {
        val result = iRetrofit.getSimilarMovies(moviesId = moviesId)
        return listOf()
    }


}