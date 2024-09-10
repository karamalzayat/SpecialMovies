package com.example.specialmovies.presentation.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.data.repository.MovieRepository
import com.example.specialmovies.presentation.screens.movies.events.ListState
import com.example.specialmovies.presentation.screens.movies.events.MoviesListState
import com.example.specialmovies.presentation.screens.movies.events.MoviesUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _screenState: MutableStateFlow<MoviesListState<Any?>> =
        MutableStateFlow(MoviesListState(null, ListState.Loading))
    val screenState: StateFlow<MoviesListState<Any?>> = _screenState.asStateFlow()
    private var currentPage = 1
    private var isLoadingMore = false
    private var allMovies = mutableListOf<Movie>()

    init {

        callMovies()
    }
    private fun callMovies() {
        if (isLoadingMore) return
        isLoadingMore = true
        viewModelScope.launch {
            val response = movieRepository.getPopularMovies(currentPage)
            if (response.result.isNotEmpty()) {
                allMovies.addAll(response.result)
                _screenState.update { state ->
                    state.copy(state = ListState.Success, data = allMovies)
                }
                currentPage++
            } else {
                _screenState.update { state ->
                    state.copy(state = ListState.Error)
                }
            }
            isLoadingMore = false
        }
    }

    fun onUiEvent(moviesUiEvent: MoviesUiEvent) {
        when (moviesUiEvent) {
            MoviesUiEvent.ReloadMoviesList -> {
                _screenState.update { state ->
                    state.copy(state = ListState.Loading)
                }
                callMovies()
            }

            is MoviesUiEvent.LoadNextPage -> {
                callMovies()
            }
        }
    }

}