package com.example.retrofit.dto

import com.squareup.moshi.Json

data class RetrofitMovies(
    val page: Int = 0,
    @field:Json(name = "results")
    val retrofitMovies: List<RetrofitMovie> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)