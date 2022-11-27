package com.example.roomdb

import androidx.paging.PagingSource
import androidx.room.RoomDatabase
import com.example.roomdb.dao.FavoriteDAO
import com.example.roomdb.dao.MovieDAO
import com.example.roomdb.dao.MovieRemoteKeysDAO
import com.example.roomdb.entities.FavoriteEntity
import com.example.roomdb.entities.MovieEntity
import com.example.roomdb.entities.MovieRemoteKeysEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DatabaseImpl @Inject constructor(
    private val database: Database,
    private val favoriteDAO: FavoriteDAO,
    private val movieDAO: MovieDAO,
    private val remoteKeysDAO: MovieRemoteKeysDAO
) {

    fun getDatabase(): RoomDatabase = database

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

    //region Movie

    fun getAllMovies(): PagingSource<Int, MovieEntity>
    {
        return movieDAO.getAllMovies()
    }

    suspend fun addMovies(movies: List<MovieEntity>)
    {
        movieDAO.addMovies(movies)
    }

    suspend fun deleteAllMovies()
    {
        movieDAO.deleteAllMovies()
    }

    //endregion

    //region Remote Keys

    suspend fun getRemoteKeys(id: Int): MovieRemoteKeysEntity
    {
        return remoteKeysDAO.getRemoteKeys(id)
    }

    suspend fun addAllRemoteKeys(remoteKeys: List<MovieRemoteKeysEntity>)
    {
        remoteKeysDAO.addAllRemoteKeys(remoteKeys)
    }

    suspend fun deleteAllRemoteKeys()
    {
        remoteKeysDAO.deleteAllRemoteKeys()
    }

    //endregion

}