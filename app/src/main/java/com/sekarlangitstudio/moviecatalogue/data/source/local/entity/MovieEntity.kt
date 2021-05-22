package com.sekarlangitstudio.moviecatalogue.data.source.local.entity

data class MovieEntity(
    val movieId: String,
    val title: String,
    val description: String,
    val airingDate: String,
    val score: String,
    val genre: String,
    val duration: String,
    val director: String,
    val casting: String,
    val imagePath: String
)