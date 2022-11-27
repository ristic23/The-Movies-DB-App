package com.example.roomdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomdb.utils.REMOTE_KEYS_TABLE_NAME

@Entity(tableName = REMOTE_KEYS_TABLE_NAME)
data class MovieRemoteKeysEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?

)