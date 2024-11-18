package com.example.specialmovies.data.repository

import androidx.paging.PagingData
import com.example.specialmovies.data.local.entity.MovieEntity
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.data.remote.responses.MovieDetailsResponse
import com.example.specialmovies.data.remote.responses.MoviesListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton


@Singleton
abstract class MovieRepository {
    abstract suspend fun getPopularMovies(page: Int): MoviesListResponse
    abstract suspend fun getMovies(): Flow<PagingData<Movie>>

    abstract suspend fun getMovieDetails(id: Long): MovieDetailsResponse

    abstract suspend fun saveFavorite(movie: MovieDetailsResponse)
    abstract suspend fun saveFavorite(movie: MovieEntity)


    abstract suspend fun removeFavorite(movie: MovieDetailsResponse)
    abstract suspend fun removeFavorite(movie: MovieEntity)

    abstract suspend fun getFavoriteMovies(): Flow<List<MovieEntity>>
    abstract suspend fun isFavoriteMovie(id: Int): Boolean

}