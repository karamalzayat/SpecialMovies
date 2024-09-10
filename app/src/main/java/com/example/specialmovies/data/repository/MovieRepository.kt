package com.example.specialmovies.data.repository

import com.example.specialmovies.data.local.entity.MovieEntity
import com.example.specialmovies.data.remote.responses.MovieDetailsResponse
import com.example.specialmovies.data.remote.responses.MoviesListResponse
import javax.inject.Singleton


@Singleton
abstract class MovieRepository {
    abstract suspend fun getPopularMovies(page: Int): MoviesListResponse

    abstract suspend fun getMovieDetails(id: Long): MovieDetailsResponse

    abstract suspend fun saveFavorite(movie: MovieEntity)

    abstract suspend fun removeFavorite(movie: MovieEntity)

    abstract suspend fun getFavoriteMovies(): List<MovieEntity>
    abstract suspend fun isFavoriteMovie(id: Int): Boolean

}