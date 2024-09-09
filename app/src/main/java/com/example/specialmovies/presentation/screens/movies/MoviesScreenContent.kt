package com.example.specialmovies.presentation.screens.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.specialmovies.R
import com.example.specialmovies.data.remote.responses.Movie
import com.example.specialmovies.presentation.screens.movies.state.MoviesUiEvent


@Composable
fun MoviesScreenContent(
    reload: Boolean,
    loading: Boolean,
    handleEvent: (event: MoviesUiEvent) -> Unit,
    moviesToShow: List<Movie> = arrayListOf()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (loading) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight(700),
                fontSize = 22.sp,
                text = stringResource(id = R.string.loading)
            )
        } else if (reload) {
            Button(modifier = Modifier.align(Alignment.Center),
                onClick = {
                    handleEvent.invoke(MoviesUiEvent.ReloadMoviesList)
                }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.reload)
                )
            }
        } else {
            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    items(moviesToShow.size) {
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillParentMaxWidth()
                                .padding(8.dp)
                                .background(
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .height(128.dp)
                                    .width(128.dp),
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = moviesToShow[it].title
                            )
                            Column(modifier = Modifier.padding(start = 16.dp)) {
                                Text(
                                    text = moviesToShow[it].title.toString(), style = TextStyle(
                                        color = Color.Black,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(700),
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(id = R.string.release_date) + moviesToShow[it].releaseDate.toString(),
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontFamily = FontFamily.Serif,
                                        fontWeight = FontWeight(500),
                                        fontSize = 16.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(id = R.string.rate) + "(" + moviesToShow[it].voteAverage.toString() + "/10)",
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
                }
            }
        }
    }


}

@Preview
@Composable
fun MoviesScreenContentPreview() {
    MoviesScreenContent(false, false, {}, arrayListOf())
}