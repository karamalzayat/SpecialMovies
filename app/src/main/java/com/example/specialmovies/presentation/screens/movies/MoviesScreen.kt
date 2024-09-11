package com.example.specialmovies.presentation.screens.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.example.specialmovies.R
import com.example.specialmovies.data.remote.responses.Movie
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton

import androidx.compose.ui.text.style.TextOverflow
import com.example.specialmovies.ui.theme.Pink40

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onNavigateToMovieDetailsRoute: (Long) -> Unit,
    onNavigateToFavoritesRoute: () -> Unit
) {
    val moviePagingItems: LazyPagingItems<Movie> = viewModel.moviesState.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .background(Pink40, RoundedCornerShape(8.dp))
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 36.dp)
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(8.dp),
        ) {
            item { Spacer(modifier = Modifier.padding(4.dp)) }
            items(moviePagingItems.itemCount) { index ->
                MyMovieItem(
                    movie = moviePagingItems[index]!!,
                    onClick = {
                        onNavigateToMovieDetailsRoute.invoke(moviePagingItems[index]!!.id)
                    }
                )
            }
            moviePagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingState(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = moviePagingItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem(modifier = Modifier) }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = moviePagingItems.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier,
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }

        }
        Button(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomCenter)
            .fillMaxWidth(),
            onClick = {
                onNavigateToFavoritesRoute.invoke()
            }
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(id = R.string.show_favorites)
            )
        }
    }


}


@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.loading),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight(700),
                fontFamily = FontFamily.Monospace,
                fontSize = 22.sp
            ),
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}

@Composable
fun LoadingNextPageItem(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(id = R.string.reload))
        }
    }
}

@Composable
fun MyMovieItem(movie: Movie, onClick: () -> Unit) {
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
                .width(128.dp)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp)),
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
