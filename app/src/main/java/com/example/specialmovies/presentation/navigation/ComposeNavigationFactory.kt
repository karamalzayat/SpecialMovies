package com.example.specialmovies.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder


internal interface ComposeNavigationFactory {
    fun create(navGraphBuilder: NavGraphBuilder, navController: NavController)
}