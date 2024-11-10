package com.example.jetmovie.movie_detail.domain.models

data class MovieDetail(
    val backdropPath: String,
    val genreIds: List<String>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val video: Boolean,
    val cast: List<Cast>,
    val language: List<String>,
    val productionCountry: List<String>,
    val reviews: List<Review>,
    val runTime: String
)

data class Cast(
    val id: Int,
    val name: String,
    val genderRole: String,
    val character: String,
    val profilePath: String?,
) {
    private val nameParts = name.split(" ", limit = 2)
    val firstName = nameParts[0]
    val lastName = nameParts[1]
}

data class Review(
    val author: String,
    val content: String,
    val id: String,
    val createdAt: String,
    val rating:Double
)