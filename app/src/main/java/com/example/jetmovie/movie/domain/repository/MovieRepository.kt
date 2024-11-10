package com.example.jetmovie.movie.domain.repository

import com.example.jetmovie.movie.domain.models.Movie
import com.example.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovie(): Flow<Response<List<Movie>>>
}