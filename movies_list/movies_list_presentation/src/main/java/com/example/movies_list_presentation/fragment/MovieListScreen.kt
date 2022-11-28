package com.example.movies_list_presentation.fragment

import android.util.Log
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core_ui.R
import com.example.core_ui.components.ResponsiveText
import com.example.movies_list_presentation.viewModel.MoviesListViewModel
import com.example.retrofit.util.IMAGE_BASE_URL
import java.text.NumberFormat
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.dtoMovies.Movie
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieListScreen(
    moviesListViewModel: MoviesListViewModel,
    movieClicked: (Int) -> Unit
) {
    val movies = moviesListViewModel.getMovies().collectAsLazyPagingItems()
    val textColor = colorResource(id = R.color.text_dark)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = movies.itemCount,
            key = {
                movies[it]?.id ?: 0
            }
        ) { index ->
            val item = movies[index]

            MovieTopHolderDetails(
                textColor = textColor,
                movie = item,
                checkedState = false,
                favoriteButtonClicked = { state, id ->
                   //todo
                },
                movieClicked = {
                    movieClicked(it)
                }
            )
        }
    }

}

@Composable
fun MovieTopHolderDetails(
    textColor: Color,
    movie: Movie?,
    checkedState: Boolean,
    favoriteButtonClicked: (Boolean, Int) -> Unit,
    movieClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable {
                movieClicked(movie?.id ?: 0)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(0.67f)
                .clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(IMAGE_BASE_URL + movie?.poster_path)
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
                text = movie?.title ?: "",
                color = textColor,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.release_date),
                movie?.release_date ?: ""
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.genre),
                ""
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.budget),
                ""
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.revenue),
                ""
            )
            HorizontalLabelWithDesc(
                textColumnModifier,
                stringResource(id = R.string.runtime),
                ""
            )
        }
//        Column(
//            modifier = Modifier
//                .fillMaxHeight()
//                .width(40.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Bottom
//        ) {
//            FavoriteToggleButton(
//                modifier = Modifier.width(35.dp),
//                checkedState,
//                favoriteButtonClicked = {
//                    favoriteButtonClicked(it, movie.id)
//                }
//            )
//        }
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