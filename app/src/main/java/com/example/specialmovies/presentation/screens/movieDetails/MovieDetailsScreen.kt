package com.example.specialmovies.presentation.screens.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.specialmovies.R
import com.example.specialmovies.data.remote.responses.MovieDetailsResponse
import com.example.specialmovies.presentation.screens.movieDetails.events.DetailsState
import com.example.specialmovies.presentation.screens.movieDetails.events.MovieDetailsUiEvent
import com.example.specialmovies.ui.theme.Pink40

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    movieId: Long
) {
    LaunchedEffect(movieId) {
        viewModel.callMovieDetails(movieId)
    }
    val state = viewModel.screenState.collectAsState().value
    val isFavorite = remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .background(Pink40, RoundedCornerShape(8.dp))
            .fillMaxSize()
            .padding(16.dp)

    ) {
        when (state.state) {

            is DetailsState.Loading -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight(700),
                    fontSize = 22.sp,
                    text = stringResource(id = R.string.loading)
                )

            }

            is DetailsState.Error -> {
                Button(modifier = Modifier.align(Alignment.Center),
                    onClick = {
                        viewModel.onUiEvent(MovieDetailsUiEvent.ReloadMovieDetails(movieId))
                    }
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = stringResource(id = R.string.reload)
                    )
                }
            }

            is DetailsState.Success -> {
                val movie = state.data as MovieDetailsResponse
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 36.dp)
                        .verticalScroll(scrollState)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp)),
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w300" + movie.posterPath),
                        contentDescription = movie.title
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    FavoriteToggleButton(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 16.dp),
                        isFavorite = isFavorite.value || state.state.isFavorite,
                        onToggleFavorite = {
                            isFavorite.value = !(isFavorite.value || state.state.isFavorite)
                            if (isFavorite.value) {
                                viewModel.onUiEvent(MovieDetailsUiEvent.AddMovieToFavorites(movie))
                            } else {
                                viewModel.onUiEvent(MovieDetailsUiEvent.RemoveMovieToFavorites(movie))

                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = movie.title, style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(700),
                            fontSize = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(36.dp))
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.votes_count) + movie.voteCount.toString(),
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Text(
                        text = stringResource(id = R.string.over_view),
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(500),
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Start)
                            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        text = movie.overview,
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight(300),
                            fontSize = 16.sp
                        )
                    )

                }
            }
        }

    }
}


@Composable
fun FavoriteToggleButton(isFavorite: Boolean, onToggleFavorite: () -> Unit, modifier: Modifier) {
    IconButton(modifier = modifier, onClick = onToggleFavorite) {
        Icon(
            modifier = Modifier
                .width(34.dp)
                .height(34.dp),
            painter = if (isFavorite) {
                painterResource(id = R.drawable.remove_favoriet)
            } else {
                painterResource(id = R.drawable.add_to_favorites)
            },
            contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
        )
    }
}