package com.example.roomdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.roomdb.entities.FavoriteEntity
import com.example.roomdb.utils.FAVORITE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM $FAVORITE_TABLE_NAME")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS(SELECT * FROM $FAVORITE_TABLE_NAME WHERE movieId = :movieId)")
    suspend fun isFavoritesAdded(movieId : Int): Boolean

    @Query("SELECT * FROM $FAVORITE_TABLE_NAME where movieId LIKE :movieId")
    suspend fun getFavoritesSpecific(movieId: Int): FavoriteEntity?

    @Insert
    suspend fun saveFavorites(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun removeFavorites(favoriteEntity: FavoriteEntity)

}