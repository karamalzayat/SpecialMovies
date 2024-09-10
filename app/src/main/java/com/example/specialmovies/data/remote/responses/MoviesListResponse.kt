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
    val id: Long=533535,

    @SerializedName("original_language")
    val originalLanguage: String="english",

    @SerializedName("original_title")
    val originalTitle: String="Deadpool & Wolverine",

    @SerializedName("overview")
    val overview: String="A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine",

    @SerializedName("popularity")
    val popularity: Double=1.2,

    @SerializedName("poster_path")
    val posterPath: String="",

    @SerializedName("release_date")
    val releaseDate: String="2024-07-24",

    @SerializedName("title")
    val title: String="Deadpool & Wolverine",

    @SerializedName("video")
    val video: Boolean=true,

    @SerializedName("vote_average")
    val voteAverage: Double=7.744,

    @SerializedName("vote_count")
    val voteCount: Long=2733,

    @SerializedName("genre_ids")
    val genreIds: List<Long> = arrayListOf(),

    )
