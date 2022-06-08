package com.example.madlevel6task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task2.R
import com.example.madlevel6task2.adapter.MovieAdapter
import com.example.madlevel6task2.databinding.FragmentSearchBinding
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.vm.MovieViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment() {

    private val movies = arrayListOf<MovieItem>()
    private lateinit var movieAdapter: MovieAdapter
    private var _binding: FragmentSearchBinding? = null
    private val viewModel: MovieViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(movies, ::onMovieClick)
        binding.moviesLayout.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.moviesLayout.adapter = movieAdapter

        binding.buttonSearch.setOnClickListener {
            onSubmitClicked()
        }
        observeVideos()
    }

    private fun observeVideos() {
        viewModel.movies.observe(viewLifecycleOwner, {
            movies.clear()
            movies.addAll(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun onSubmitClicked() {
        val yearDateString: String = binding.yearInput.text.toString().trim()
        if (yearDateString.isEmpty()) {
            Toast.makeText(activity, "Year must be filled in", Toast.LENGTH_SHORT).show()
            return
        }


        searchForMovies(yearDateString.toInt())
    }

    private fun searchForMovies(year: Int) {
        movies.clear()
        movieAdapter.notifyDataSetChanged()

        viewModel.getMoviesByYear(year)
    }

    private fun onMovieClick(movieItem: MovieItem) {

        print(movieItem)
        print(movieItem.title)
        viewModel.select(movies[0])
        findNavController().navigate(R.id.action_FirstFragment_to_viewFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}