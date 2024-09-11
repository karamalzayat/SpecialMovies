package com.example.specialmovies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.specialmovies.presentation.screens.favorites.FavoritesScreen

internal class FavoritesNavigationFactory : ComposeNavigationFactory {

    override fun create(navGraphBuilder: NavGraphBuilder, navController: NavController) {
        navGraphBuilder.composable(route = "favoritesList") {
            FavoritesScreen()
        }
    }
}