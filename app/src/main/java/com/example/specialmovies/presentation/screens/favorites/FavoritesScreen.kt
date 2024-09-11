package com.example.specialmovies.presentation.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.specialmovies.R
import com.example.specialmovies.data.local.entity.MovieEntity
import com.example.specialmovies.presentation.screens.favorites.events.FavoritesListState
import com.example.specialmovies.presentation.screens.movieDetails.FavoriteToggleButton
import com.example.specialmovies.ui.theme.Pink40

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
) {


    val state = viewModel.screenState.collectAsStateWithLifecycle()
    val moviesList = ArrayList<MovieEntity>()


    Box(
        modifier = Modifier
            .background(Pink40, RoundedCornerShape(8.dp))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (state.value.state) {
            is FavoritesListState.Error -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.no_favorites),
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight(700),
                    fontSize = 22.sp,
                )

            }

            is FavoritesListState.Loading -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.no_favorites),
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight(700),
                    fontSize = 22.sp,
                )
            }

            is FavoritesListState.Success -> {
                moviesList.clear()
                moviesList.addAll(state.value.data as List<MovieEntity>)
                Box {
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .fillMaxSize()
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .padding(8.dp),
                        content = {
                            items(moviesList.size) { index ->
                                MovieItem(
                                    movie = moviesList[index],
                                    onRemoveFavorite = { viewModel.removeFromDB(moviesList[index]) },
                                    onAddFavorite = { viewModel.addToDB(moviesList[index]) }
                                )
                            }
                        },
                    )

                }
            }
        }
    }
}


@Composable
fun MovieItem(
    movie: MovieEntity,
    onRemoveFavorite: () -> Unit,
    onAddFavorite: () -> Unit

) {
    val isFavorite = remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .height(128.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .width(128.dp).padding(1.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp)),
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w300" + movie.posterPath),
            contentDescription = movie.title
        )
        Column(modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)) {
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

            FavoriteToggleButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding( bottom = 4.dp),
                isFavorite = isFavorite.value,
                onToggleFavorite = {
                    isFavorite.value = !isFavorite.value
                    if (!isFavorite.value) {
                        onRemoveFavorite()
                    } else {
                        onAddFavorite()
                    }
                }
            )
        }


    }
}
