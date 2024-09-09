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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        MainAppNavHost()
    }
}

@Composable
fun MainAppNavHost(
    navController: NavHostController = rememberNavController()
) {

        NavHost(navController, startDestination = Routes.MoviesList) {
            MoviesListNavigationFactory().create(this, navController)
            MovieDetailsNavigationFactory().create(this, navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   MainApp()
}