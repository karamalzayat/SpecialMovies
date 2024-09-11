package com.example.specialmovies.data.repository


import androidx.lifecycle.LiveData
import com.example.specialmovies.data.local.dao.MovieDao
import com.example.specialmovies.data.local.entity.MovieEntity
import com.example.specialmovies.data.remote.responses.MovieDetailsResponse
import com.example.specialmovies.data.remote.responses.MoviesListResponse
import com.example.specialmovies.data.remote.retrofit.WebServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepositoryImp @Inject constructor(
    private val apiService: WebServices,
    private val movieDao: MovieDao,
) : MovieRepository() {
    private val apiKey: String = "b8d7f72abee18fd93012e158e9211297"

    override suspend fun getPopularMovies(page: Int): MoviesListResponse {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMovies(apiKey, page)
                (if (response.isSuccessful) {
                    response.body()
                } else {
                    MoviesListResponse()
                })!!
            } catch (e: Exception) {
                MoviesListResponse()
            }
        }
    }

    // Get Movie Details by ID
    override suspend fun getMovieDetails(id: Long): MovieDetailsResponse {
        return withContext(Dispatchers.IO) {

            try {
                val response = apiService.getMovieDetails(id, apiKey)
                (if (response.isSuccessful) {
                    response.body()
                } else {
                    MovieDetailsResponse()
                })!!
            } catch (e: Exception) {
                null
            }!!
        }
    }

    // Save Favorite Movie Locally
    override suspend fun saveFavorite(movie: MovieDetailsResponse) {
        withContext(Dispatchers.IO) {
            movieDao.insertFavorite(movieDetailsToMovieEntity(movie))
        }
    }

    override suspend fun saveFavorite(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.insertFavorite(movie)
        }
    }

    // Remove Favorite Movie from Local Database
    override suspend fun removeFavorite(movie: MovieDetailsResponse) {
        withContext(Dispatchers.IO) {
            movieDao.deleteFavorite(movieDetailsToMovieEntity(movie))
        }
    }

    override suspend fun removeFavorite(movie: MovieEntity) {
        withContext(Dispatchers.IO) {
            movieDao.deleteFavorite(movie)
        }
    }

    // Get Favorite Movies from Local Database
    override suspend fun getFavoriteMovies(): Flow<List<MovieEntity>> {
        return withContext(Dispatchers.IO) {
            movieDao.getAllFavorites()
        }
    }

    // Check if a Movie is Favorite
    override suspend fun isFavoriteMovie(id: Int): Boolean {
        return withContext(Dispatchers.IO) {
            movieDao.getFavoriteById(id) != null
        }
    }

    private fun movieDetailsToMovieEntity(movieDetailsResponse: MovieDetailsResponse): MovieEntity {
        return MovieEntity(
            id = movieDetailsResponse.id,
            title = movieDetailsResponse.title,
            voteAverage = movieDetailsResponse.voteAverage,
            releaseDate = movieDetailsResponse.releaseDate,
            posterPath = movieDetailsResponse.posterPath,
            isFavorite = true
        )
    }
}
