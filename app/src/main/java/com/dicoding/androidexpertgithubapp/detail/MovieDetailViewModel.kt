package com.dicoding.androidexpertgithubapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.androidexpertgithubapp.core.domain.model.Movie
import com.dicoding.androidexpertgithubapp.core.domain.usecase.MovieUseCase

class MovieDetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setMovieFavoriteStatus(movie: Movie, newState: Boolean) {
        movieUseCase.setFavoriteMovie(movie, newState)
    }
}