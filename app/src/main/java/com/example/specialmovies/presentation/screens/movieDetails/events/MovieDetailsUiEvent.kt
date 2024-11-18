package com.example.specialmovies.presentation.screens.movieDetails.events

import com.example.specialmovies.data.remote.responses.MovieDetailsResponse

sealed class MovieDetailsUiEvent {
    data class AddMovieToFavorites(val movie: MovieDetailsResponse) : MovieDetailsUiEvent()
    data class RemoveMovieToFavorites(val movie:MovieDetailsResponse) : MovieDetailsUiEvent()
    data class ReloadMovieDetails(val movieId:Long) : MovieDetailsUiEvent()

}