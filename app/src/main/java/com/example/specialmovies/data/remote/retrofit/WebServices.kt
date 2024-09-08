package com.example.specialmovies.data.remote.retrofit

import com.example.specialmovies.data.remote.responses.MovieDetailsResponse
import com.example.specialmovies.data.remote.responses.MoviesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MoviesListResponse>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieDetailsResponse>
}