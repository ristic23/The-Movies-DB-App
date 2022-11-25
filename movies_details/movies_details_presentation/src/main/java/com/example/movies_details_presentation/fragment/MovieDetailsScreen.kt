package com.example.movies_details_presentation.fragment

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core_ui.R
import com.example.core_ui.components.ResponsiveText
import com.example.movies_details_presentation.viewModel.MoviesDetailsViewModel
import com.example.retrofit.util.IMAGE_BASE_URL
import java.text.NumberFormat
import java.util.*

@Composable
fun MovieDetailsScreen(
    modifier: Modifier,
    moviesDetailsViewModel: MoviesDetailsViewModel
) {
    val movie by moviesDetailsViewModel.movieResultLiveData.observeAsState(initial = emptyDetailMovie())
    val textColor = colorResource(id = R.color.text_dark)
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
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
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.95f)
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