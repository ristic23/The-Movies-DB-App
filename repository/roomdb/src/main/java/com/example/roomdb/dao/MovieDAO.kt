package com.example.roomdb.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.roomdb.entities.FavoriteEntity
import com.example.roomdb.entities.MovieEntity
import com.example.roomdb.utils.FAVORITE_TABLE_NAME
import com.example.roomdb.utils.MOVIE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Query("SELECT * FROM $MOVIE_TABLE_NAME")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM $MOVIE_TABLE_NAME")
    suspend fun deleteAllMovies()


}