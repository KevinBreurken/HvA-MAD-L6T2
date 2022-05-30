package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.api.MovieApi
import com.example.madlevel6task2.api.MovieApiService
import com.example.madlevel6task2.model.MovieItem
import kotlinx.coroutines.withTimeout

class MovieRepository {
    private val movieApiService: MovieApiService = MovieApi.createApi()

    private val _trivia: MutableLiveData<List<MovieItem>> = MutableLiveData()

    /**
     * Expose non MutableLiveData via getter
     * Encapsulation :)
     */
    val movies: LiveData<List<MovieItem>>
        get() = _trivia

    fun getMovieItems(): List<MovieItem> {
        return arrayListOf(
            MovieItem(
                338953,
                "jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg",
                "/1Ds7xy7ILo8u2WWxdnkJth1jQVT.jpg",
                "The Lost City",
                "2022-03-22",
                "6.5",
                "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero. His test comes when Dr. Robotnik returns, this time with a new partner, Knuckles, in search for an emerald that has the power to destroy civilizations. Sonic teams up with his own sidekick, Tails, and together they embark on a globe-trotting journey to find the emerald before it falls into the wrong hands."
            )
        );
    }

    /**
     * suspend function that calls a suspend function from the numbersApi call
     */
    suspend fun getMovieItemss() {
        try {
            //timeout the request after 5 seconds
            val result = withTimeout(5_000) {
                movieApiService.getRandomNumberTrivia()
            }

//            _trivia.value = result
        } catch (error: Throwable) {
            throw MovieRefreshError("Unable to load movies", error)
        }
    }

    class MovieRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
}