package com.example.madlevel6task2.model

data class MovieItem(
    val id: Number,
    val posterPath: String,
) {
    fun getPosterImage() = "https://image.tmdb.org/t/p/original/$posterPath"
}