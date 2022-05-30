package com.example.madlevel6task2.api

import com.example.madlevel6task2.model.MovieItem
import retrofit2.http.GET

interface MovieApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET("/random/trivia?json")
    suspend fun getRandomNumberTrivia(): MovieItem
}