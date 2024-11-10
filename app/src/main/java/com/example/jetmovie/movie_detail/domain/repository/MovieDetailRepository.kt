package com.example.jetmovie.movie_detail.domain.repository

import com.example.jetmovie.movie.domain.models.Movie
import com.example.jetmovie.movie_detail.domain.models.MovieDetail
import com.example.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>>
    fun fetchMovie(): Flow<Response<List<Movie>>>
}