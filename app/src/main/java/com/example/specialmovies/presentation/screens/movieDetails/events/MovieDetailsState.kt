package com.example.specialmovies.presentation.screens.movieDetails.events

data class MovieDetailsState<T>(
    val data: T,
    val state: DetailsState,
)

sealed class DetailsState {
    data object Loading : DetailsState()
    data object Error : DetailsState()
    data object Success : DetailsState()
}