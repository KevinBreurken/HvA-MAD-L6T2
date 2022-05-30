package com.example.madlevel6task2.repository

import com.example.madlevel6task2.model.MovieItem

class MovieRepository {
    /**
     * No data from IO here, just a dummy list with colors
     * */
    fun getMovieItems(): List<MovieItem> {
        return arrayListOf(
            MovieItem(338953, "jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg"),
            MovieItem(338953, "6DrHO1jr3qVrViUO6s6kFiAGM7.jpg"),
            MovieItem(338953, "jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg"),
            MovieItem(338953, "jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg"),
            MovieItem(338953, "jrgifaYeUtTnaH7NF5Drkgjg2MB.jpg"),
        )
    }
}