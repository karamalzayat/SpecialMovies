package com.example.specialmovies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.specialmovies.presentation.common.State
import com.example.specialmovies.presentation.common.UiState
import com.example.specialmovies.presentation.navigation.MovieDetailsNavigationFactory
import com.example.specialmovies.presentation.navigation.MoviesListNavigationFactory
import com.example.specialmovies.presentation.navigation.Routes
import com.example.specialmovies.presentation.screens.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp(viewModel.uiState)
        }
    }
}

@Composable
fun MainApp(uiState: StateFlow<UiState<Routes, Any>>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        MainAppNavHost(uiState)
    }
}

@Composable
fun MainAppNavHost(
    uiState: StateFlow<UiState<Routes, Any>>,
    navController: NavHostController = rememberNavController()
) {
    val state = uiState.collectAsStateWithLifecycle()
    val startDestination = state.value.data
    if (startDestination != Routes.MoviesList) {
        NavHost(navController, startDestination = startDestination) {
            MoviesListNavigationFactory().create(this, navController)
            MovieDetailsNavigationFactory().create(this, navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val state =
        MutableStateFlow<UiState<Routes, Any>>(UiState(Routes.MoviesList, State.Success("")))
    MainApp(state)
}