package com.example.roomdb.di

import android.content.Context
import androidx.room.Room
import com.example.roomdb.Database
import com.example.roomdb.dao.FavoriteDAO
import com.example.roomdb.utils.APPLICATION_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideSavedReadingDao(database: Database): FavoriteDAO =
        database.getFavoriteDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(
            context,
            Database::class.java,
            APPLICATION_DATABASE_NAME
        ).build()

}