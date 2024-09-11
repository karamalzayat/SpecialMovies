package com.example.specialmovies.presentation.screens.favorites

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.specialmovies.data.local.entity.MovieEntity
import com.example.specialmovies.data.repository.MovieRepository
import com.example.specialmovies.presentation.screens.favorites.events.FavoritesListState
import com.example.specialmovies.presentation.screens.favorites.events.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _screenState: MutableStateFlow<FavoritesState<Any?>> =
        MutableStateFlow(FavoritesState(null, FavoritesListState.Loading))
    val screenState: StateFlow<FavoritesState<Any?>> = _screenState.asStateFlow()


    private var allMovies = mutableListOf<MovieEntity>()



    init {
        callMovies()
    }

    private fun callMovies() {

        viewModelScope.launch {
            movieRepository.getFavoriteMovies().collect {
                if (it.isEmpty()) {
                    _screenState.update { state ->
                        state.copy(state = FavoritesListState.Error)
                    }
                } else {
                    allMovies.clear()
                    allMovies.addAll(it)
                    _screenState.update { state ->
                        state.copy(state = FavoritesListState.Success, data = allMovies)
                    }
                }
            }

        }
    }

    fun onUiEvent(movieEntity: MovieEntity) {
        viewModelScope.launch {
            movieRepository.removeFavorite(movie = movieEntity)
        }
    }

}
