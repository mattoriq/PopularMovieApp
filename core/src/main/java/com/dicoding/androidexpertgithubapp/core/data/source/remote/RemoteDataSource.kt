package com.dicoding.androidexpertgithubapp.core.data.source.remote

import android.util.Log
import com.dicoding.androidexpertgithubapp.core.data.source.remote.network.ApiResponse
import com.dicoding.androidexpertgithubapp.core.data.source.remote.network.ApiService
import com.dicoding.androidexpertgithubapp.core.data.source.remote.response.MovieResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService){

    suspend fun getMovieList(): Flow<ApiResponse<List<MovieResultsItem>>> {
        return flow {
            try {
                val response = apiService.getPopularMovies("aeb90365b6aa3c2538a7b7b1ed94baf8")
                if (response.results.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch(e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}