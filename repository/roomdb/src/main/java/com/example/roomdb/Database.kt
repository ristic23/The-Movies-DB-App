package com.example.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb.dao.FavoriteDAO
import com.example.roomdb.entities.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false)
abstract class Database : RoomDatabase()
{
    abstract fun getFavoriteDao(): FavoriteDAO

}