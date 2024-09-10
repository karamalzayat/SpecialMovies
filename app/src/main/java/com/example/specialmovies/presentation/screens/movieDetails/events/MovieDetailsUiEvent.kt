package com.example.specialmovies.presentation.screens.movieDetails.events

sealed class MovieDetailsUiEvent {
    data object AddMovieToFavorites : MovieDetailsUiEvent()
    data object RemoveMovieToFavorites : MovieDetailsUiEvent()
    data object ReloadMovieDetails : MovieDetailsUiEvent()

}