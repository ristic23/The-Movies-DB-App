package com.example.core.dtoMovies.castAndCrew

data class MovieCastAndCrew(
    val cast: List<MovieCast> = listOf(),
    val crew: List<MovieCrew> = listOf(),
    val id: Int = 0
)
