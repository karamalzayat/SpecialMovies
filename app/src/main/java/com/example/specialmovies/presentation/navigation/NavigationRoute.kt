package com.example.specialmovies.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object MoviesList : Routes()

    @Serializable
    data class MovieDetails(val movie: String) : Routes()
}
