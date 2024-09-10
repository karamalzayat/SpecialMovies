package com.example.specialmovies.presentation.screens.movieDetails.events

sealed class MovieDetailsUiEvent {
    data class AddMovieToFavorites(val movieId:Long) : MovieDetailsUiEvent()
    data class RemoveMovieToFavorites(val movieId:Long) : MovieDetailsUiEvent()
    data class ReloadMovieDetails(val movieId:Long) : MovieDetailsUiEvent()

}