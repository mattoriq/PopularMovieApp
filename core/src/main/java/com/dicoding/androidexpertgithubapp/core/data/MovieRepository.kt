package com.dicoding.androidexpertgithubapp.core.data

import com.dicoding.androidexpertgithubapp.core.data.source.local.LocalDataSource
import com.dicoding.androidexpertgithubapp.core.data.source.remote.RemoteDataSource
import com.dicoding.androidexpertgithubapp.core.data.source.remote.network.ApiResponse
import com.dicoding.androidexpertgithubapp.core.data.source.remote.response.MovieResultsItem
import com.dicoding.androidexpertgithubapp.core.domain.model.Movie
import com.dicoding.androidexpertgithubapp.core.domain.repository.IMovieRepository
import com.dicoding.androidexpertgithubapp.core.utils.AppExecutors
import com.dicoding.androidexpertgithubapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : com.dicoding.androidexpertgithubapp.core.data.NetworkBoundResource<List<Movie>, List<MovieResultsItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getMovieList()

            override suspend fun saveCallResult(data: List<MovieResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}