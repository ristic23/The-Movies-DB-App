package com.example.movies_details_presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.movies_details_presentation.databinding.FragmentMoviesDetailsBinding
import com.example.movies_details_presentation.viewModel.MoviesDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment: Fragment() {
    private val viewModel: MoviesDetailsViewModel by viewModels()

    private lateinit var binding: FragmentMoviesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_birthday_moon, null, false)
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)

        val movieId = arguments?.getInt("movieId") ?: 0

        viewModel.getMovie(movieId)

        viewModel.movieResultLiveData.observe(viewLifecycleOwner) {
//            binding.btnGoto2.text = it.title
        }

        binding.btnGoto2.setContent {
            MovieDetailsScreen(
                modifier =  Modifier.fillMaxSize(),
                moviesDetailsViewModel = viewModel
            )
        }

        return binding.root
    }


}