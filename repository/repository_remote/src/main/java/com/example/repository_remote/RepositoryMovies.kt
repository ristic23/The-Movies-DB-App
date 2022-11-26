package com.example.repository_remote

import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.castAndCrew.MovieCastAndCrew
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core.dtoMovies.favorite.MovieFavorite
import com.example.core.dtoMovies.recommendations.RecommendationMovies
import com.example.repository_remote.mapper.*
import com.example.retrofit.RetrofitController
import com.example.roomdb.DatabaseImpl
import com.example.roomdb.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryMovies @Inject constructor(
    private val retrofitController: RetrofitController,
    private val databaseImpl: DatabaseImpl
): IRepositoryMovies
{
    override suspend fun getTopRatedMovies(): Movies  {
        val result = retrofitController.getTopRatedMovies()
        return retrofitMoviesToMovies(result)
    }

    override suspend fun getMovieDetails(moviesId: Int): DetailsMovie {
        val result = retrofitController.getMoviesDetails(moviesId)
        return retrofitMovieDetailToMovieDetail(result)
    }

    override suspend fun searchMovies() {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieCastCrew(moviesId: Int):MovieCastAndCrew {
        val result = retrofitController.getMovieCastAndCrew(moviesId)
        return retrofitCastAndCrewToMovieCastAndCrew(result)
    }

    override suspend fun getMovieRecommendations(moviesId: Int): RecommendationMovies {
        val result = retrofitController.getMovieRecommendations(moviesId)
        return retrofitRecommendedMoviesToRecommendedMovies(result)
    }

    override suspend fun addMovieToFavorites(favorite: MovieFavorite) {
        databaseImpl.addMovieToFavorites(movieFavoriteToFavoriteEntity(favorite))
    }

    override suspend fun removeMovieFromFavorites(favorite: MovieFavorite) {
        databaseImpl.removeMovieFromFavorites(movieFavoriteToFavoriteEntity(favorite))
    }

    override suspend fun getAllFavorites(): Flow<List<MovieFavorite>> = flow {
        databaseImpl.getFavorites().collect { list ->
                emit(list
                    .map { entity->
                        favoriteEntityToMovieFavorite(entity)
                    }
                )
            }
        }

    override suspend fun isFavoritesAdded(movieId : Int): Boolean = databaseImpl.isFavoritesAdded(movieId)

    override suspend fun getFavoritesSpecific(movieId : Int): MovieFavorite? =
        favoriteEntityToMovieFavorite(databaseImpl.getFavoritesSpecific(movieId))

}