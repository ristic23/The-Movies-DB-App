package com.example.retrofit.dto

data class RetrofitMovies(
    val page: Int,
    val retrofitMovies: List<RetrofitMovie>,
    val total_pages: Int,
    val total_results: Int
)