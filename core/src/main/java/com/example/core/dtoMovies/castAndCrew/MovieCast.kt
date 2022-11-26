package com.example.core.dtoMovies.castAndCrew

data class MovieCast(
    val character: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val original_name: String,
    val profile_path: String
)