package com.example.core.dtoMovies.recommendations

data class RecommendationMovies(
    val page: Int = 1,
    val results: List<RecommendationMovie> = listOf(),
    val total_pages: Int = 1,
    val total_results: Int = 20
)
