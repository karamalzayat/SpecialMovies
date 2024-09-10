package com.example.specialmovies.presentation.screens.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.specialmovies.R
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.presentation.screens.movies.events.ListState
import com.example.specialmovies.presentation.screens.movies.events.MoviesUiEvent

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onNavigateToMovieDetailsRoute: (Long) -> Unit,
) {
    val moviesList: ArrayList<Movie> = arrayListOf()
    val state = viewModel.screenState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (state.value.state) {
            is ListState.Error -> {
                Button(modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        viewModel.onUiEvent(MoviesUiEvent.ReloadMoviesList)
                    }
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = stringResource(id = R.string.reload)
                    )
                }
            }

            is ListState.LoadNextMovies -> {
                moviesList.addAll(state.value.data as List<Movie>)
            }

            is ListState.Loading -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight(700),
                    fontSize = 22.sp,
                    text = stringResource(id = R.string.loading)
                )
            }

            is ListState.Success -> {
                moviesList.addAll(state.value.data as List<Movie>)
                Box {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        content = {
                            items(moviesList.size) { index ->
                                if (index == moviesList.size - 1) {
                                    viewModel.onUiEvent(MoviesUiEvent.LoadNextPage)
                                }
                                MovieItem(
                                    movie = moviesList[index],
                                    onClick = {
                                        onNavigateToMovieDetailsRoute.invoke(moviesList[index].id)
                                    })
                            }
                        },
                    )
                }
            }
        }
    }
}


@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Image(

            modifier = Modifier
                .height(128.dp)
                .width(128.dp),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w300" + movie.posterPath),
            contentDescription = movie.title
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = movie.title, style = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.release_date) + movie.releaseDate,
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.rate) + "(" + movie.voteAverage.toString() + "/10)",
                style = TextStyle(
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight(500),
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun DashboardStartScreenPreview() {
}