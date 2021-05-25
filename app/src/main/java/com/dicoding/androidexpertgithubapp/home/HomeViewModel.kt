package com.dicoding.androidexpertgithubapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.androidexpertgithubapp.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movieList = movieUseCase.getAllMovie().asLiveData()
}