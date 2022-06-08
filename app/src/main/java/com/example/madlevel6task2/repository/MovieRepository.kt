package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.api.MovieApiService
import com.example.madlevel6task2.model.MovieItem
import kotlinx.coroutines.withTimeout

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()
    private val _movies: MutableLiveData<List<MovieItem>> = MutableLiveData()
    val movies: LiveData<List<MovieItem>> get() = _movies

    suspend fun getMoviesOfYear(year: Int) {
        try {
            val result = withTimeout(8000) {
                movieApiService.getMoviesByYear(year)
            }

            _movies.value = result.results
        } catch (error: Throwable) {
            throw MovieNotFoundError("No movies found", error)
        }
    }

    class MovieNotFoundError(message: String, cause: Throwable) : Throwable(message, cause)
}