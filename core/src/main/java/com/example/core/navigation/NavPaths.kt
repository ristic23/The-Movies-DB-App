package com.example.core.navigation

object NavPaths {
    private const val pathPrefix = "moviesDbApp://"
    const val moviesList = "movies_list"
    const val moviesListPath = "$pathPrefix$moviesList"
    const val moviesDetails = "movie_detail"
    const val moviesDetailsPath = "$pathPrefix$moviesDetails"
    const val moviesSearch = "movie_search"
    const val moviesSearchPath = "$pathPrefix$moviesSearch"
    const val moviesFavorites = "movie_favorites"
    const val moviesFavoritesPath = "$pathPrefix$moviesFavorites"


    fun concatenateMovieId(movieId: Int): String = "${moviesDetailsPath}/$movieId"
}