package com.example.specialmovies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.specialmovies.presentation.screens.movies.MoviesScreen

internal class MoviesListNavigationFactory : ComposeNavigationFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Routes.MoviesList> {
            MoviesScreen(
                onNavigateToMovieDetailsRoute = {
                    navController.navigate(route = Routes.MovieDetails("154989")) {
                    }
                })
        }
    }
}