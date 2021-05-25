package com.dicoding.androidexpertgithubapp.core.data.source.remote.network

import com.dicoding.androidexpertgithubapp.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key")apiKey: String
    ): MovieListResponse
}