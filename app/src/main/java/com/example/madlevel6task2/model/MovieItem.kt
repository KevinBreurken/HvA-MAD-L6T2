package com.example.madlevel6task2.model

data class MovieItem(
    val id: Number,
    val posterPath: String,
) {
    fun getPosterImageById() = "https://image.tmdb.org/t/p/original/$posterPath"
}