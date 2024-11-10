package com.example.jetmovie.movie_detail.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    @SerialName("cast")
    val cast: List<CastDto?>? = null,
    @SerialName("crew")
    val crew: List<Crew?>? = null
)