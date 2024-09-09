package com.example.specialmovies.presentation.screens.movies

import androidx.lifecycle.ViewModel
import com.example.specialmovies.data.repository.MovieRepository
import com.example.specialmovies.presentation.common.State
import com.example.specialmovies.presentation.common.UiState
import com.example.specialmovies.presentation.navigation.Routes
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<Routes, Any>>(UiState(Routes.MoviesList, State.Idle()))
    val uiState: StateFlow<UiState<Routes, Any>> = _uiState.asStateFlow()


}