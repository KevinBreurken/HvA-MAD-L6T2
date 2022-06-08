package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: String,
    @SerializedName("overview") val overview: String,
) {
    fun getPosterImage() = "https://image.tmdb.org/t/p/original/$posterPath"
    fun getBackdropImage() = "https://image.tmdb.org/t/p/original/$backdropPath"
}