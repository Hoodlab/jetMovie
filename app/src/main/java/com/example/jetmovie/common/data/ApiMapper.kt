package com.example.jetmovie.common.data

interface ApiMapper<Domain,Entity> {
    fun mapToDomain(apiDto:Entity):Domain
}