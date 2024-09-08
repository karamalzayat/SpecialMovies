package com.example.specialmovies.data.repository

import com.example.specialmovies.data.local.dao.MovieDao
import com.example.specialmovies.data.local.entity.MovieEntity
import com.example.specialmovies.data.remote.responses.MovieDetailsResponse
import com.example.specialmovies.data.remote.responses.MoviesListResponse
import com.example.specialmovies.data.remote.retrofit.WebServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository @Inject constructor(
    private val apiService: WebServices,
    private val movieDao: MovieDao,
    private val apiKey: String
) {

    // Get Popular Movies with Pagination
    suspend fun getPopularMovies(page: Int): MoviesListResponse? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getMovies(apiKey, page)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    // Get Movie Details by ID
    suspend fun getMovieDetails(id: Int): MovieDetailsResponse? {
        return withContext(Dispatchers.IO) {
            val response = apiService.getMovieDetails(id, apiKey)
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        }
    }

    // Save Favorite Movie Locally
    suspend fun saveFavorite(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.insertFavorite(movie)
        }
    }

    // Remove Favorite Movie from Local Database
    suspend fun removeFavorite(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.deleteFavorite(movie)
        }
    }

    // Get Favorite Movies from Local Database
    suspend fun getFavoriteMovies(): List<MovieEntity> {
        return withContext(Dispatchers.IO) {
            movieDao.getAllFavorites()
        }
    }

    // Check if a Movie is Favorite
    suspend fun isFavoriteMovie(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            movieDao.getFavoriteById(id) != null
        }
    }
}