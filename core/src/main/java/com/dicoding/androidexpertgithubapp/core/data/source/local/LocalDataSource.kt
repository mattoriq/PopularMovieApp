package com.dicoding.androidexpertgithubapp.core.data.source.local

import com.dicoding.androidexpertgithubapp.core.data.source.local.entity.MovieEntity
import com.dicoding.androidexpertgithubapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getAllFavMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movieEntity: MovieEntity, newState: Boolean) {
        movieEntity.isFavorite = newState
        movieDao.updateFavoriteMovie(movieEntity)
    }

}