package com.example.madlevel6task2.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.repository.MovieRepository

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    //use encapsulation to expose as LiveData
    val movieItems: LiveData<List<MovieItem>>
        get() = _movieItems

    private val _movieItems = MutableLiveData<List<MovieItem>>().apply {
        value = movieRepository.getMovieItems()
    }
}