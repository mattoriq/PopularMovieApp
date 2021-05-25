package com.dicoding.androidexpertgithubapp.di

import com.dicoding.androidexpertgithubapp.detail.MovieDetailViewModel
import com.dicoding.androidexpertgithubapp.core.domain.usecase.MovieInteractor
import com.dicoding.androidexpertgithubapp.core.domain.usecase.MovieUseCase
import com.dicoding.androidexpertgithubapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}