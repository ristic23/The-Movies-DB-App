package com.example.retrofit.dto.movieRecommendations

data class RetrofitMovieRecommendation(
    val page: Int = 1,
    val results: List<RetrofitRecommendationResult> = listOf(),
    val total_pages: Int = 1,
    val total_results: Int = 20
)