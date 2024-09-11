package com.example.specialmovies.presentation.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.data.repository.MovieRepository
import com.example.specialmovies.domain.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getMoviesUseCase: GetMovieUseCase
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState


    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        getMoviesUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }
}



