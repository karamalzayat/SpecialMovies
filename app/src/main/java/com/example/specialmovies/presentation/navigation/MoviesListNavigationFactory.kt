package com.example.specialmovies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.specialmovies.presentation.screens.movies.MoviesScreen

internal class MoviesListNavigationFactory : ComposeNavigationFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(route = "moviesList") {
            MoviesScreen(
                onNavigateToMovieDetailsRoute = { movieId ->
                    navController.navigate("movieDetails/$movieId")
                }, onNavigateToFavoritesRoute = {
                    navController.navigate("favoritesList")
                }
            )
        }
    }
}