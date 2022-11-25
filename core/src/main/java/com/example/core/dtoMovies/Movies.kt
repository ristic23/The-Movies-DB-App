package com.example.core.dtoMovies

data class Movies(
    val page: Int = 0,
    val retrofitMovies: List<Movie> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)
