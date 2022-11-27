package com.example.roomdb.dao

import androidx.room.*
import com.example.roomdb.entities.FavoriteEntity
import com.example.roomdb.entities.MovieRemoteKeysEntity
import com.example.roomdb.utils.FAVORITE_TABLE_NAME
import com.example.roomdb.utils.REMOTE_KEYS_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieRemoteKeysDAO {

    @Query("SELECT * FROM $REMOTE_KEYS_TABLE_NAME WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): MovieRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<MovieRemoteKeysEntity>)

    @Query("DELETE FROM $REMOTE_KEYS_TABLE_NAME")
    suspend fun deleteAllRemoteKeys()

}