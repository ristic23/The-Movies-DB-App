package com.example.movies_details_presentation.fragment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.dtoMovies.castAndCrew.MovieCast
import com.example.core.dtoMovies.castAndCrew.MovieCastAndCrew
import com.example.core.dtoMovies.castAndCrew.MovieCrew
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core.dtoMovies.recommendations.RecommendationMovie
import com.example.core.dtoMovies.recommendations.RecommendationMovies
import com.example.core_ui.R
import com.example.core_ui.components.FavoriteToggleButton
import com.example.core_ui.components.ResponsiveText
import com.example.movies_details_presentation.viewModel.MoviesDetailsViewModel
import com.example.retrofit.util.CAST_CREW_IMAGE_BASE_URL
import com.example.retrofit.util.IMAGE_BASE_URL
import java.text.NumberFormat

@Composable
fun MovieDetailsScreen(
    modifier: Modifier,
    moviesDetailsViewModel: MoviesDetailsViewModel,
    recommendedMovieClick: (Int) -> Unit,
    favoriteButtonClicked: (Boolean, Int) -> Unit
) {
    val movie by moviesDetailsViewModel.movieResultLiveData.observeAsState(initial = emptyDetailMovie())

    val castAndCrew by moviesDetailsViewModel.movieCastAndCrewFlow.collectAsState(initial = MovieCastAndCrew())

    val recommendedMovies by moviesDetailsViewModel.recommendedMoviesFlow.collectAsState(initial = RecommendationMovies())

    val checkedState by moviesDetailsViewModel.isMovieInFavoritesFlow.collectAsState(initial = false)

    val textColor = colorResource(id = R.color.text_dark)
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieTopHolderDetails(
            textColor = textColor,
            movie = movie,
            checkedState = checkedState,
            favoriteButtonClicked = { state, id ->
                favoriteButtonClicked(state, id)
            }
        )
        Spacer(modifier = Modifier
            .height(16.dp)
            .width(1.dp))
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            text = movie.overview,
            color = textColor,
            textAlign = TextAlign.Justify,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier
            .height(16.dp)
            .width(1.dp))
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            text = "Cast & crew >",
            color = textColor,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        CrewAndCastLazyRow(
            textColor,
            castAndCrew
        )
        Spacer(modifier = Modifier
            .height(16.dp)
            .width(1.dp))

        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            text = "Movies recommendations >",
            color = textColor,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier
            .height(4.dp)
            .width(1.dp))
        RecommendedMoviesLazyRow(
            textColor = textColor,
            recommendedMovies = recommendedMovies,
            recommendedMovieClick = {
                recommendedMovieClick(it)
            }
        )
        Spacer(modifier = Modifier
            .height(16.dp)
            .width(1.dp))


    }
}

@Composable
fun MovieTopHolderDetails(
    textColor: Color,
    movie: DetailsMovie,
    checkedState: Boolean,
    favoriteButtonClicked: (Boolean, Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(0.67f)
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
                .fillMaxHeight()
                .fillMaxWidth(0.85f)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            val textColumnModifier  = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                text = movie.title,
                color = textColor,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.release_date),
                movie.release_date
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.genre),
                concatenateNames(movie.genres.map { it.name })
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.budget),
                formatPrice(movie.budget)
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.revenue),
                formatPrice(movie.revenue)
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.runtime),
                "${movie.runtime}min"
            )



        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            FavoriteToggleButton(
                modifier = Modifier.width(35.dp),
                checkedState,
                favoriteButtonClicked = {
                    favoriteButtonClicked(it, movie.id)
                }
            )
        }
    }
}

@Composable
fun HorizontalLabelWithDesc(
    modifier: Modifier,
    labelDesc: String,
    labelText: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        ResponsiveText(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            text =  labelDesc,
            color = colorResource(id = R.color.text_dark),
            targetTextSizeHeight = 14.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .width(12.dp))
        ResponsiveText(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth(),
            text =  labelText,
            color = colorResource(id = R.color.text_dark),
            targetTextSizeHeight = 16.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CrewAndCastLazyRow(
    textColor: Color,
    castAndCrew: MovieCastAndCrew
) {
    val mergeList = mutableListOf<Any>()
    mergeList.addAll(castAndCrew.cast)
    mergeList.addAll(castAndCrew.crew)

    LazyRow(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    ) {
        items(count = mergeList.size) { index ->
            val item = mergeList[index]
            val imagePath: String = when(item) {
                is MovieCast -> item.profile_path
                is MovieCrew -> item.profile_path
                else -> ""
            }
            val name: String = when(item) {
                is MovieCast -> item.name
                is MovieCrew -> item.name
                else -> ""
            }
            val characterNameOrJob: String = when(item) {
                is MovieCast -> item.character
                is MovieCrew -> item.job
                else -> ""
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(CAST_CREW_IMAGE_BASE_URL + imagePath)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.placeholder)
                )
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    text = name,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    text = "(${characterNameOrJob})",
                    color = textColor,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light
                )
            }

            Spacer(modifier = Modifier
                .width(4.dp)
                .fillMaxHeight())
        }
    }

}

@Composable
fun RecommendedMoviesLazyRow(
    textColor: Color,
    recommendedMovies: RecommendationMovies,
    recommendedMovieClick: (Int) -> Unit
) {

    LazyRow(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        items(count = recommendedMovies.results.size) { index ->
            val item = recommendedMovies.results[index]

            MovieSimpleItem(
                recommendedMovieClick,
                textColor,
                item
            )

            Spacer(modifier = Modifier
                .width(4.dp)
                .fillMaxHeight())
        }
    }
}

@Composable
fun MovieSimpleItem(
    recommendedMovieClick: (Int) -> Unit,
    textColor: Color,
    item: RecommendationMovie
)
{
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(130.dp)
            .clickable {
                recommendedMovieClick(item.id)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(0.667f)
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(IMAGE_BASE_URL + item.poster_path)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.placeholder)
        )
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            text = item.title,
            color = textColor,
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            text = item.release_date,
            color = textColor,
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
            fontWeight = FontWeight.Light
        )
    }
}



private fun emptyDetailMovie(): DetailsMovie = DetailsMovie(
    "",
    0,
    listOf(),
    0,
    "",
    "",
    "",
    0.0,
    "",
    "",
    0,
    0,
    "",
    "",
    "",
    0.0,
    0
)
private fun concatenateNames(genreIds: List<String>): String {
    var returnValue = ""
    genreIds.forEach {
        returnValue += "$it "
    }
    return returnValue
}
fun formatPrice(price: Int): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0

    return format.format(price)
}