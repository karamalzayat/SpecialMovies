package com.example.specialmovies.domain

import androidx.paging.PagingData
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
     suspend fun execute(input: Unit): Flow<PagingData<Movie>> {
        return repository.getMovies()
    }
}