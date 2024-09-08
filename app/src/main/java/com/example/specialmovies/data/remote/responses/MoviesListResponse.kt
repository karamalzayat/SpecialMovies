package com.example.specialmovies.data.remote.responses

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("result")
    val result: List<Movie>?,

    @SerializedName("total_pages")
    val totalPages: Long?,

    @SerializedName("total_results")
    val totalResults: Long?,
)

data class Movie(
    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("id")
    val id: Long?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Long?,

    @SerializedName("genre_ids")
    val genreIds: List<Long>?

    )
