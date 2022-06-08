package com.example.madlevel6task2.ui

import android.animation.ObjectAnimator
import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.ViewPropertyTransition
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.FragmentViewBinding
import com.example.madlevel6task2.model.MovieItem
import com.example.madlevel6task2.repository.MovieRepository
import com.example.madlevel6task2.vm.MovieViewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ViewFragment : Fragment() {

    private var _binding: FragmentViewBinding? = null
    private val viewModel: MovieViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel._selectedItem.observe(viewLifecycleOwner) { loadMovieItem(it)
       Toast.makeText(context,it.backdropPath,Toast.LENGTH_LONG).show()}
    }

    fun loadMovieItem(movieItem:MovieItem){
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .error(R.mipmap.ic_launcher_round)

        context?.let { Glide.with(it).load(movieItem.getPosterImage()).apply(options).transition(
            DrawableTransitionOptions.withCrossFade(1000)).into(binding.detailPosterView) }

        val animationObject =
            ViewPropertyTransition.Animator { view ->
                view.alpha = 0f
                view.scaleX = 0f
                val fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
                val scaleAnim = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
                fadeAnim.duration = 500
                fadeAnim.start()
                scaleAnim.duration = 1000
                scaleAnim.start()
            }

        context?.let { Glide.with(it).load(movieItem.getBackdropImage()).apply(options).transition(
            GenericTransitionOptions.with(animationObject)).into(binding.detailBackdropView) }

        print(viewModel._selectedItem)
        binding.titleLabel.text = movieItem.title
        binding.releaseLabel.text = movieItem.releaseDate

        binding.overviewText.text = movieItem.overview
        binding.ratingText.text = movieItem.voteAverage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}