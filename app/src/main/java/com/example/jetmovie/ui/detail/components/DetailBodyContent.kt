package com.example.jetmovie.ui.detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetmovie.movie.domain.models.Movie
import com.example.jetmovie.movie_detail.domain.models.MovieDetail
import com.example.jetmovie.movie_detail.domain.models.Review
import com.example.jetmovie.ui.home.components.MovieCard
import com.example.jetmovie.ui.home.components.MovieCoverImage
import com.example.jetmovie.ui.home.defaultPadding
import com.example.jetmovie.ui.home.itemSpacing

@Composable
fun DetailBodyContent(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail,
    movies: List<Movie>,
    isMovieLoading: Boolean,
    fetchMovies: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onActorClick: (Int) -> Unit,
) {
    LazyColumn(modifier) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(defaultPadding)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            movieDetail.genreIds.forEachIndexed { index, genreText ->
                                Text(
                                    text = genreText,
                                    modifier = Modifier
                                        .padding(6.dp),
                                    maxLines = 1,
                                    style = MaterialTheme.typography.bodySmall
                                )
                                // Show divider after all except the last item
                                if (index != movieDetail.genreIds.lastIndex) {
                                    Text(
                                        text = " \u2022 ",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                        Text(
                            text = movieDetail.runTime,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Spacer(modifier = Modifier.height(itemSpacing))
                    Text(
                        text = movieDetail.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(itemSpacing))
                    Text(
                        text = movieDetail.overview,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.height(itemSpacing))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        ActionIcon.entries.forEachIndexed { index, actionIcon ->
                            ActionIconBtn(
                                icon = actionIcon.icon,
                                contentDescription = actionIcon.contentDescription,
                                bgColor = if (index == ActionIcon.entries.lastIndex)
                                    MaterialTheme.colorScheme.primaryContainer
                                else Color.Black.copy(
                                    .5f
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(itemSpacing))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = itemSpacing),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cast & Crew",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "Cast & Crew"
                            )
                        }
                    }
                    LazyRow {
                        items(movieDetail.cast) {
                            ActorItem(
                                cast = it,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { onActorClick(it.id) }
                            )
                            Spacer(modifier = Modifier.width(defaultPadding))
                        }
                    }
                    Spacer(modifier = Modifier.height(itemSpacing))

                    MovieInfoItem(
                        infoItem = movieDetail.language,
                        title = "Spoken language",
                    )
                    Spacer(modifier = Modifier.height(itemSpacing))
                    MovieInfoItem(
                        infoItem = movieDetail.productionCountry,
                        title = "Production countries",
                    )
                    Spacer(modifier = Modifier.height(itemSpacing))
                    Text(
                        text = "Reviews",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(itemSpacing))
                    Review(reviews = movieDetail.reviews)
                    Spacer(modifier = Modifier.height(itemSpacing))
                    MoreLikeThis(
                        fetchMovies = fetchMovies,
                        isMovieLoading = isMovieLoading,
                        movies = movies,
                        onMovieClick = onMovieClick
                    )

                }
            }
        }


    }

}


@Composable
fun MoreLikeThis(
    modifier: Modifier = Modifier,
    fetchMovies: () -> Unit,
    isMovieLoading: Boolean,
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
) {
    LaunchedEffect(key1 = true) {
        fetchMovies()
    }
    Column(modifier) {
        Text(
            text = "More like this",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        LazyRow {
            item {
                AnimatedVisibility(visible = isMovieLoading) {
                    CircularProgressIndicator()
                }
            }
            items(movies) {
                MovieCoverImage(movie = it, onMovieClick = onMovieClick)
            }
        }
    }

}

private enum class ActionIcon(val icon: ImageVector, val contentDescription: String) {
    BookMark(icon = Icons.Default.Bookmark, "bookmark"),
    Share(icon = Icons.Default.Share, "Share"),
    Download(icon = Icons.Default.Download, "Download"),
}

@Composable
private fun ActionIconBtn(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    bgColor: Color = Color.Black.copy(.8f)
) {
    MovieCard(
        shapes = CircleShape,
        modifier = modifier
            .padding(4.dp),
        bgColor = bgColor
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
private fun MovieInfoItem(infoItem: List<String>, title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(4.dp))
        infoItem.forEach {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun Review(
    modifier: Modifier = Modifier,
    reviews: List<Review>
) {
    val (viewMore, setViewMore) = remember {
        mutableStateOf(false)
    }
    // show only three reviews or less by default
    val defaultReview =
        if (reviews.size > 3) reviews.take(3) else reviews
    // show more when user needs more review
    val movieReviews = if (viewMore) reviews else defaultReview
    val btnText = if (viewMore) "Collapse" else "More..."
    Column(modifier) {
        movieReviews.forEach { review ->
            ReviewItem(review = review)
            Spacer(modifier = Modifier.height(itemSpacing))
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(itemSpacing))
        }
        TextButton(onClick = { setViewMore(!viewMore) }) {
            Text(text = btnText)
        }
    }

}