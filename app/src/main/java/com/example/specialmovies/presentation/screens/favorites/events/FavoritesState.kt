package com.example.specialmovies.presentation.screens.favorites.events


data class FavoritesState<T>(
    val data: T,
    val state: FavoritesListState,
)

sealed class FavoritesListState {
    data object Loading : FavoritesListState()
    data object Error : FavoritesListState()
    data object Success : FavoritesListState()
}