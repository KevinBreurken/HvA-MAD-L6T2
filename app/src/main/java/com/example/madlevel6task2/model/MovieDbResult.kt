package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName


data class MovieDbResult(
    @SerializedName("results") val results: List<MovieItem>
)