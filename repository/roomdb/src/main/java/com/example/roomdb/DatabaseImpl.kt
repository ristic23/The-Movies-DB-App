package com.example.roomdb

import com.example.roomdb.dao.FavoriteDAO
import com.example.roomdb.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatabaseImpl @Inject constructor(
    private val favoriteDAO: FavoriteDAO
) {

    //region Favorite

    suspend fun addMovieToFavorites(favoriteEntity: FavoriteEntity)
    {
        favoriteDAO.saveFavorites(favoriteEntity)
    }

    suspend fun removeMovieFromFavorites(favoriteEntity: FavoriteEntity)
    {
        favoriteDAO.removeFavorites(favoriteEntity)
    }

    fun getFavorites(): Flow<List<FavoriteEntity>> = favoriteDAO.getFavorites()

    suspend fun isFavoritesAdded(movieId : Int): Boolean = favoriteDAO.isFavoritesAdded(movieId)

    suspend fun getFavoritesSpecific(movieId : Int): FavoriteEntity? = favoriteDAO.getFavoritesSpecific(movieId)

    //endregion

}