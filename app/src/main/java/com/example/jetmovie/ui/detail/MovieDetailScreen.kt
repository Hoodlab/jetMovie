package com.example.jetmovie.ui.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetmovie.ui.components.LoadingView
import com.example.jetmovie.ui.detail.components.DetailBodyContent
import com.example.jetmovie.ui.detail.components.DetailTopContent

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieDetailViewMode: DetailViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onActorClick: (Int) -> Unit
) {
    val state by movieDetailViewMode.detailState.collectAsStateWithLifecycle()
    Box(modifier = modifier.fillMaxWidth()) {
        AnimatedVisibility(
            state.error != null,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Text(
                state.error ?: "unknown",
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }
        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val boxHeight = maxHeight
                val topItemHeight = boxHeight * .4f
                val bodyItemHeight = boxHeight * .6f
                state.movieDetail?.let { movieDetail ->
                    DetailTopContent(
                        movieDetail = movieDetail,
                        modifier = Modifier
                            .height(topItemHeight)
                            .align(Alignment.TopCenter)
                    )
                    DetailBodyContent(
                        movieDetail = movieDetail,
                        movies = state.movies,
                        isMovieLoading = state.isMovieLoading,
                        fetchMovies = movieDetailViewMode::fetchMovie,
                        onMovieClick = onMovieClick,
                        onActorClick = onActorClick,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .height(bodyItemHeight)
                    )
                }
            }
        }
        IconButton(onClick = onNavigateUp, modifier = Modifier.align(Alignment.TopStart)) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
        }
    }
    LoadingView(isLoading = state.isLoading)
}





