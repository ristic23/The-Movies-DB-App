package com.example.repository_remote.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.example.core.dtoMovies.Movies
import com.example.repository_remote.mapper.retrofitMovieToMovieEntity
import com.example.retrofit.RetrofitController
import com.example.retrofit.util.ITEMS_PER_PAGE
import com.example.roomdb.DatabaseImpl
import com.example.roomdb.entities.MovieEntity
import com.example.roomdb.entities.MovieRemoteKeysEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val retrofitController: RetrofitController,
    private val databaseImpl: DatabaseImpl
): RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = retrofitController.getTopRatedMovies(page = currentPage)
            val endOfPaginationReached = response.retrofitMovies.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            val db: RoomDatabase = databaseImpl.getDatabase()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    databaseImpl.deleteAllMovies()
                    databaseImpl.deleteAllRemoteKeys()
                }
                val moviesList = response.retrofitMovies
                val keys = moviesList.map { movie ->
                    MovieRemoteKeysEntity(
                        id = movie.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                val moviesEntities = moviesList.map { movie ->
                    retrofitMovieToMovieEntity(movie)
                }
                databaseImpl.addAllRemoteKeys(remoteKeys = keys)
                databaseImpl.addMovies(movies = moviesEntities)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                databaseImpl.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let{
                databaseImpl.getRemoteKeys(id = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MovieEntity>
    ): MovieRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                databaseImpl.getRemoteKeys(id = it.id)
            }
    }



}