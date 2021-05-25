package com.dicoding.androidexpertgithubapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<MovieResultsItem>
)

data class MovieResultsItem(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Float,

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String
)