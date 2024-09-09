package com.example.specialmovies.presentation.screens.movies

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.specialmovies.presentation.screens.movieDetails.MovieDetailsViewModel


@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onNavigateToMovieDetailsRoute: () -> Unit
) {

}