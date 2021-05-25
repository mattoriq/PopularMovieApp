package com.dicoding.androidexpertgithubapp.core.domain.usecase

import com.dicoding.androidexpertgithubapp.core.data.Resource
import com.dicoding.androidexpertgithubapp.core.domain.model.Movie
import com.dicoding.androidexpertgithubapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovie()

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}