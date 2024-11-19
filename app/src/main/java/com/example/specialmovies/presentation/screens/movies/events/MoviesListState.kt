package com.example.specialmovies.presentation.screens.movies.events

data class MoviesListState<T>(
    val data: T,
    val state: ListState,
)

sealed class ListState {
    data object Loading : ListState()
    data object Error : ListState()
    data object Success : ListState()

}