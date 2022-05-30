package com.example.madlevel6task2.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    //use encapsulation to expose as LiveData
    val movieItems: LiveData<List<MovieItem>>
        get() = _movieItems

    private val _movieItems = MutableLiveData<List<MovieItem>>().apply {
        value = movieRepository.getMovieItems()
    }

    fun getMovies() {
        viewModelScope.launch {
//            try {
//                //the triviaRepository sets it's own livedata property
//                //our own trivia property points to this one
//                movieRepository.getMovieItemss()
//            } catch (error: MovieRepository.MovieRefreshError) {
//                _movieItems.value = error.message
//                Log.e("Triva error", error.cause.toString())
//            }
        }
    }
}