package com.example.specialmovies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.specialmovies.presentation.screens.movieDetails.MovieDetailsScreen

internal class MovieDetailsNavigationFactory : ComposeNavigationFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable<Routes.MovieDetails> {
            MovieDetailsScreen(
                onNavigateToMoviesListRoute = {
                    navController.navigate(route = Routes.MoviesList) {
                    }
                })
        }
    }
}