package com.example.repository_remote

import androidx.paging.*
import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.castAndCrew.MovieCastAndCrew
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core.dtoMovies.favorite.MovieFavorite
import com.example.core.dtoMovies.recommendations.RecommendationMovies
import com.example.roomdb.entities.FavoriteEntity
import com.example.roomdb.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IRepositoryMovies {

    suspend fun getTopRatedMovies(page: Int): Movies

    suspend fun getMovieDetails(moviesId: Int): DetailsMovie

    suspend fun searchMovies(query: String): Movies

    suspend fun getMovieCastCrew(moviesId: Int): MovieCastAndCrew

    suspend fun getMovieRecommendations(moviesId: Int): RecommendationMovies

    suspend fun addMovieToFavorites(favorite: MovieFavorite)

    suspend fun removeMovieFromFavorites(favorite: MovieFavorite)

    suspend fun getAllFavorites(): Flow<List<MovieFavorite>>

    suspend fun isFavoritesAdded(movieId : Int): Boolean

    suspend fun getFavoritesSpecific(movieId : Int): MovieFavorite?

    @OptIn(ExperimentalPagingApi::class)
    fun getTopRatedMoviesPaging(): Flow<PagingData<MovieEntity>>
}