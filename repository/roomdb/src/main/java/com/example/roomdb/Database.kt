package com.example.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb.dao.FavoriteDAO
import com.example.roomdb.dao.MovieDAO
import com.example.roomdb.dao.MovieRemoteKeysDAO
import com.example.roomdb.entities.FavoriteEntity
import com.example.roomdb.entities.MovieEntity
import com.example.roomdb.entities.MovieRemoteKeysEntity

@Database(
    entities = [FavoriteEntity::class, MovieEntity::class, MovieRemoteKeysEntity::class],
    version = 1,
    exportSchema = false)
abstract class Database : RoomDatabase()
{
    abstract fun getFavoriteDao(): FavoriteDAO
    abstract fun getMovieDao(): MovieDAO
    abstract fun getRemoteKeysDao(): MovieRemoteKeysDAO

}