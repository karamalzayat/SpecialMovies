package com.example.specialmovies.presentation.screens.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.specialmovies.data.remote.responses.MovieDetailsResponse
import com.example.specialmovies.data.repository.MovieRepository
import com.example.specialmovies.presentation.screens.movieDetails.events.DetailsState
import com.example.specialmovies.presentation.screens.movieDetails.events.MovieDetailsState
import com.example.specialmovies.presentation.screens.movieDetails.events.MovieDetailsUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _screenState: MutableStateFlow<MovieDetailsState<Any?>> =
        MutableStateFlow(MovieDetailsState(null, DetailsState.Loading))
    val screenState: StateFlow<MovieDetailsState<Any?>> = _screenState


    fun callMovieDetails(movieDetailsId: Long) {
        if (_screenState.value.state is DetailsState.Success) return
        _screenState.update { state ->
            state.copy(state = DetailsState.Loading)
        }
        viewModelScope.launch {
            try {
                val response = movieRepository.getMovieDetails(movieDetailsId)
                if (response != null)
                    _screenState.update { state ->
                        state.copy(state = DetailsState.Success, data = response)
                    }
            } catch (e: Exception) {
                _screenState.update { state ->
                    state.copy(state = DetailsState.Error)
                }
            }
        }
    }


    private fun updateFavoritesList(addToFavorites: Boolean, movie: MovieDetailsResponse) {
        if (addToFavorites) {
            viewModelScope.launch {
                movieRepository.saveFavorite(movie)
            }
        } else {
            viewModelScope.launch {
                movieRepository.removeFavorite(movie)
            }
        }
    }

    fun onUiEvent(movieDetailsUiEvent: MovieDetailsUiEvent) {
        when (movieDetailsUiEvent) {
            is MovieDetailsUiEvent.AddMovieToFavorites -> {
                updateFavoritesList(true, movieDetailsUiEvent.movie)
            }

            is MovieDetailsUiEvent.RemoveMovieToFavorites -> {
                updateFavoritesList(false, movieDetailsUiEvent.movie)

            }

            is MovieDetailsUiEvent.ReloadMovieDetails -> {
                callMovieDetails(movieDetailsId = movieDetailsUiEvent.movieId)
            }
        }
    }
}