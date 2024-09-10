package com.example.specialmovies.data.remote.responses

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("adult")
    val adult: Boolean = true,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection = BelongsToCollection(),
    @SerializedName("budget")
    val budget: Long = 1,
    @SerializedName("genres")
    val genres: List<Genre> = arrayListOf(),
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Long = 1,
    @SerializedName("imdb_id")
    val imdbId: String = "",
    @SerializedName("origin_country")
    val originCountry: List<String> = arrayListOf(),
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 2.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = arrayListOf(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry> = arrayListOf(),
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("revenue")
    val revenue: Long = 1,
    @SerializedName("runtime")
    val runtime: Long = 1,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = arrayListOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = true,
    @SerializedName("vote_average")
    val voteAverage: Double = 2.0,
    @SerializedName("vote_count")
    val voteCount: Long = 1
)

data class BelongsToCollection(
    @SerializedName("id")
    val id: Long = 1,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = ""
)

data class Genre(
    @SerializedName("id")
    val id: Long = 1,
    @SerializedName("name")
    val name: String = ""
)

data class ProductionCompany(
    @SerializedName("id")
    val id: Long = 1,
    @SerializedName("logo_path")
    val logoPath: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: String = ""
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso: String = "",
    @SerializedName("name")
    val name: String = ""
)

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String = "",
    @SerializedName("iso_639_1")
    val iso: String = "",
    @SerializedName("name")
    val name: String = ""
)