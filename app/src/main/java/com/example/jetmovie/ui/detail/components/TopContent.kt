package com.example.jetmovie.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.jetmovie.R
import com.example.jetmovie.movie_detail.domain.models.MovieDetail
import com.example.jetmovie.ui.home.components.MovieCard
import com.example.jetmovie.ui.home.defaultPadding
import com.example.jetmovie.ui.home.itemSpacing
import com.example.jetmovie.ui.theme.primaryLightHighContrast
import com.example.jetmovie.utils.K

@Composable
fun DetailTopContent(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("${K.BASE_IMAGE_URL}${movieDetail.posterPath}")
        .crossfade(true)
        .build()
    Box(modifier = modifier.fillMaxWidth()){
        AsyncImage(
            model = imgRequest,
            contentDescription = null, // decorative element
            modifier = Modifier
                .matchParentSize(),
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            },
            placeholder = painterResource(id = R.drawable.bg_image_movie)
        )
        MovieDetailComponent(
            rating = movieDetail.voteAverage,
            releaseDate = movieDetail.releaseDate,
            modifier = Modifier
                .align(Alignment.BottomStart)
        )
    }


}

@Composable
private fun MovieDetailComponent(
    modifier: Modifier = Modifier,
    rating: Double,
    releaseDate: String,
) {
    Column(modifier) {
        MovieCard(
            modifier = Modifier.padding(horizontal = defaultPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = rating.toString())
                }
                Spacer(modifier = Modifier.width(itemSpacing))
                VerticalDivider(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.width(itemSpacing))
                Text(
                    text = releaseDate,
                    modifier = Modifier
                        .padding(6.dp),
                    maxLines = 1
                )

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = defaultPadding)
        ) {
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp),
            ) {
                Row(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "play")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Watch Now")
                }
            }
            Card(
                onClick = { /*TODO*/ },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = primaryLightHighContrast
                ),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)
            ) {
                Row(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Movie, contentDescription = "play")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Watch Trailer")
                }
            }

        }
    }

}