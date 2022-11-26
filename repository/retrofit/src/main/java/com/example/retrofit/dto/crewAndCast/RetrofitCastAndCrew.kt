package com.example.retrofit.dto.crewAndCast

data class RetrofitCastAndCrew(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 0
)