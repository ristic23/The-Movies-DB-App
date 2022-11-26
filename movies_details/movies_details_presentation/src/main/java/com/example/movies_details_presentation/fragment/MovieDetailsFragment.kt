package com.example.movies_details_presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.core.navigation.NavPaths
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

        viewModel.updateMovieId(arguments?.getInt("movieId") ?: 0)

        viewModel.getMovie()

        binding.btnGoto2.setContent {
            MovieDetailsScreen(
                modifier =  Modifier.fillMaxSize(),
                moviesDetailsViewModel = viewModel,
                recommendedMovieClick = {
                    val request = NavDeepLinkRequest.Builder
                        .fromUri(NavPaths.concatenateMovieId(it).toUri())
                        .build()
                    findNavController().navigate(request)
                },
                favoriteButtonClicked = { state, id ->
                    viewModel.updateFavoritesClick(state)
                }
            )
        }

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.updateMovieInFavorites()
    }

}