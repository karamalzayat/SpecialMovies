package com.example.specialmovies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.specialmovies.presentation.screens.movieDetails.MovieDetailsScreen

internal class MovieDetailsNavigationFactory : ComposeNavigationFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(
            route = "movieDetails/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.LongType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getLong("movieId")
            if (movieId != null) {
                MovieDetailsScreen(
                    movieId = movieId
                )
            }
        }
    }
}
