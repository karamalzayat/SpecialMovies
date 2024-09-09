package com.example.specialmovies.data.remote.responses

import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("page")
    val page: Int=1,

    @SerializedName("results")
    val result: List<Movie> = arrayListOf(),

    @SerializedName("total_pages")
    val totalPages: Long=2,

    @SerializedName("total_results")
    val totalResults: Long=21,

)

data class Movie(
    @SerializedName("adult")
    val adult: Boolean=true,

    @SerializedName("backdrop_path")
    val backdropPath: String="",

    @SerializedName("id")
    val id: Long=1,

    @SerializedName("original_language")
    val originalLanguage: String="",

    @SerializedName("original_title")
    val originalTitle: String="",

    @SerializedName("overview")
    val overview: String="",

    @SerializedName("popularity")
    val popularity: Double=1.2,

    @SerializedName("poster_path")
    val posterPath: String="",

    @SerializedName("release_date")
    val releaseDate: String="",

    @SerializedName("title")
    val title: String="",

    @SerializedName("video")
    val video: Boolean=true,

    @SerializedName("vote_average")
    val voteAverage: Double=1.2,

    @SerializedName("vote_count")
    val voteCount: Long=1,

    @SerializedName("genre_ids")
    val genreIds: List<Long> = arrayListOf(),

    )
