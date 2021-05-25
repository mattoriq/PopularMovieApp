package com.dicoding.androidexpertgithubapp.core.utils

import com.dicoding.androidexpertgithubapp.core.data.source.local.entity.MovieEntity
import com.dicoding.androidexpertgithubapp.core.data.source.remote.response.MovieResultsItem
import com.dicoding.androidexpertgithubapp.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie =
                MovieEntity(
                    movieId = it.id,
                    title = it.title,
                    releaseDate = it.releaseDate,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    overview = it.overview,
                    isFavorite = false
                )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.movieId,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                overview = it.overview,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
            movieId = input.id,
            title = input.title,
            releaseDate = input.releaseDate,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            overview = input.overview,
            isFavorite = input.isFavorite
        )
}