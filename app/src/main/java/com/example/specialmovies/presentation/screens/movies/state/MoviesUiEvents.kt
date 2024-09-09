package com.example.specialmovies.presentation.screens.movies.state

sealed class MoviesUiEvent {
    data object ReloadMoviesList : MoviesUiEvent()
    data object SelectMovie : MoviesUiEvent()
}