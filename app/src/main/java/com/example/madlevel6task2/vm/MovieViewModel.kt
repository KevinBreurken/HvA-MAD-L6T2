package com.example.madlevel6task2.vm

import android.graphics.Movie
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

    val _selectedItem: MutableLiveData<MovieItem> = MutableLiveData()
    fun select(movie: MovieItem) {
        this._selectedItem.value = movie
    }

    private val _errorText: MutableLiveData<String> = MutableLiveData()
    val errorText: LiveData<String>
        get() = _errorText

    val movies = movieRepository.movies;
    fun getMoviesByYear(year: Int) {
        viewModelScope.launch {
            try {
                movieRepository.getMoviesOfYear(year)
            } catch (error: MovieRepository.MovieNotFoundError) {
                _errorText.value = error.message
                Log.e("Fetch error", error.cause.toString())
            }
        }
    }
}