package com.example.core.navigation

object NavPaths {
    private const val pathPrefix = "moviesDbApp://"
    const val moviesList = "movies_list"
    const val moviesListPath = "$pathPrefix$moviesList"
    const val moviesDetails = "movie_detail"
    const val moviesDetailsPath = "$pathPrefix$moviesDetails"


    fun concatenateMovieId(movieId: Int): String = "${moviesDetailsPath}/$movieId"
}