package com.example.specialmovies.presentation.screens.movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.presentation.common.NavigationActions
import com.example.specialmovies.presentation.common.State


@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(), onNavigateToMovieDetailsRoute: () -> Unit
) {
    val state = viewModel.screenState.collectAsStateWithLifecycle()
    when (state.value.state) {
        is State.Loading -> {
            MoviesScreenContent(
                loading = true,
                reload = false,
                handleEvent = viewModel::onUiEvent,
            )
        }

        is State.Error -> {
            MoviesScreenContent(
                loading = false,
                reload = true,
                handleEvent = viewModel::onUiEvent,
            )
        }

        is State.Success -> {
            if (State.Success(NavigationActions.SEEDETALES) == state.value.state) {
                onNavigateToMovieDetailsRoute.invoke()
            } else {
                MoviesScreenContent(
                    loading = false,
                    reload = false,
                    handleEvent = viewModel::onUiEvent,
                    moviesToShow = (state.value.state as State.Success<List<Movie>>).data
                )
            }
        }
    }
}

@Preview
@Composable
fun DashboardStartScreenPreview() {
    MoviesScreenContent(
        loading = false, reload = false, handleEvent = {}, moviesToShow = arrayListOf(Movie())
    )
}