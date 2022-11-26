package com.example.movies_search_presentation.fragment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.dtoMovies.Movie
import com.example.core.dtoMovies.Movies
import com.example.core_ui.R
import com.example.movies_search_presentation.viewModel.MovieSearchViewModel
import com.example.retrofit.util.IMAGE_BASE_URL


@Composable
fun MovieSearchScreen(
    modifier: Modifier,
    moviesSearchViewModel: MovieSearchViewModel,
    movieClick: (Int) -> Unit
) {
    val movies by moviesSearchViewModel.moviesResultLiveData.observeAsState(initial = Movies())

    val textColor = colorResource(id = R.color.text_dark)

    Column(
        modifier = modifier
    ) {
        SearchMoviesResult(
            textColor = textColor,
            movies = movies,
            movieClick = {
                movieClick(it)
            }
        )
    }
}

@Composable
fun SearchMoviesResult(
    textColor: Color,
    movies: Movies,
    movieClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
    ) {
        items(count = movies.retrofitMovies.size) { index ->
            val item = movies.retrofitMovies[index]

            MovieSimpleItem(
                movieClick,
                textColor,
                item
            )

            Spacer(modifier = Modifier
                .height(1.dp)
                .fillMaxWidth())
        }
    }
}

@Composable
fun MovieSimpleItem(
    movieClick: (Int) -> Unit,
    textColor: Color,
    movie: Movie
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                movieClick(movie.id)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight(1f)
                .aspectRatio(0.667f)
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(IMAGE_BASE_URL + movie.poster_path)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.placeholder)
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
                text = movie.title,
                color = textColor,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            val releaseDateSplit = movie.release_date.split("-")
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth(),
                text = if(releaseDateSplit.size == 3)
                    releaseDateSplit[0]
                else
                    movie.release_date,
                color = textColor,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        }

        Spacer(modifier = Modifier
            .height(1.dp)
            .width(16.dp))

    }
}
