package com.example.specialmovies.presentation.screens.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.specialmovies.data.repository.MovieRepository
import com.example.specialmovies.presentation.common.NavigationActions
import com.example.specialmovies.presentation.common.State
import com.example.specialmovies.presentation.common.UiState
import com.example.specialmovies.presentation.navigation.Routes
import com.example.specialmovies.presentation.screens.movies.state.MoviesUiEvent
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
    private val _screenState: MutableStateFlow<UiState<Any?, Any>> =
        MutableStateFlow(UiState(null, State.Loading()))
    val screenState: StateFlow<UiState<Any?, Any>> = _screenState.asStateFlow()

    init {
       callMovies()
    }

    private fun callMovies(){
        viewModelScope.launch {
            val response = movieRepository.getPopularMovies(1)
            if (response.result.isNotEmpty()) {
                _screenState.update { state ->
                    state.copy(state = State.Success(response.result))
                }
            } else
                _screenState.update { state ->
                    state.copy(state = State.Error("Error"))
                }
        }
    }

    fun onUiEvent(moviesUiEvent: MoviesUiEvent) {
        when (moviesUiEvent) {
            MoviesUiEvent.ReloadMoviesList -> {
                _screenState.update { state ->
                    state.copy(state = State.Loading())
                }
                callMovies()
            }

            MoviesUiEvent.SelectMovie -> {
                _screenState.update { state ->
                    state.copy(state = State.Success(NavigationActions.SEEDETALES))
                }
            }
        }
    }

}