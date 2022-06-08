package com.example.madlevel6task2.api

import com.example.madlevel6task2.model.MovieDbResult
import com.example.madlevel6task2.model.MovieItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET("3/discover/movie?api_key=4fd72e59b6d755aef988ebeeb9824d3b&language=en-US&sort_by=popularity.desc&page=1")
    suspend fun getMoviesByYear(@Query("year") year: Int): MovieDbResult

}