package com.example.specialmovies.presentation.screens.movies.events


sealed class MoviesUiEvent {
    data object ReloadMoviesList : MoviesUiEvent()
    data object LoadNextPage:MoviesUiEvent()

}