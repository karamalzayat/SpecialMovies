package com.example.specialmovies.presentation.common

data class UiState<T, R>(
    val data: T,
    val state: State<R>,
)

sealed class State<T> {
    class Loading<T>: State<T>()
    class Idle<T>: State<T>()
    class ShowDialog<T>: State<T>()
    data class Error<T>(val error: String) : State<T>()
    data class Success<T>(val data: T): State<T>()
}