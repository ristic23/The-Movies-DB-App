package com.example.core.dtoMovies.detailMovie

data class DetailsMovie(
    val backdrop_path: String,
    val budget: Int,
    val genres: List<MovieGenre>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: Any,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int,
    var isAddedInFavorites: Boolean = false
)