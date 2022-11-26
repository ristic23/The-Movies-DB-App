package com.example.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdb.utils.FAVORITE_TABLE_NAME

@Entity(tableName = FAVORITE_TABLE_NAME)
data class FavoriteEntity (
    @PrimaryKey(autoGenerate = true) var uid: Int,
        @ColumnInfo(name = "MovieId") var movieId: Int


)