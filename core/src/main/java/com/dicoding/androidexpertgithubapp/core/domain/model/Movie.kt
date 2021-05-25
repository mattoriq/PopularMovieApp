package com.dicoding.androidexpertgithubapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    var title: String,
    var posterPath: String,
    var releaseDate: String,
    var voteAverage: Float,
    var voteCount: Int,
    var overview: String,
    var isFavorite: Boolean
): Parcelable
