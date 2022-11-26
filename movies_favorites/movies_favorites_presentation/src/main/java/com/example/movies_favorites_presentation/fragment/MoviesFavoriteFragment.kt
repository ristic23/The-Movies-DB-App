package com.example.movies_favorites_presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.navigation.NavPaths
import com.example.movies_favorites_presentation.R
import com.example.movies_favorites_presentation.adapter.MoviesAdapter
import com.example.movies_favorites_presentation.databinding.FragmentMoviesFavoritesBinding
import com.example.movies_favorites_presentation.viewModel.MoviesFavoritesViewModel
import com.example.movies_favorites_presentation.viewModel.MoviesFavoritesViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFavoriteFragment: Fragment() {

    private lateinit var binding: FragmentMoviesFavoritesBinding

    @Inject
    lateinit var viewModelFactory: MoviesFavoritesViewModelFactory

    private lateinit var viewModel: MoviesFavoritesViewModel

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var gridManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesFavoritesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_favorites, null, false)

        with(binding) {
            moviesFavoritesViewModel = viewModel

            lifecycleOwner = this@MoviesFavoriteFragment

            return root
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter(
            myDataSet = mutableListOf(),
            movieOnClick = ::movieOnClick,
            addToFavoritesOnClick = ::addToFavoritesOnClick
        )

        gridManager = GridLayoutManager(activity as Context, 1)

        binding.movies.apply {
            adapter = moviesAdapter
            layoutManager = gridManager
        }

        viewModel.moviesResultLiveData.observe(viewLifecycleOwner) {
            moviesAdapter.updateList(it)
        }
    }

    private fun addToFavoritesOnClick(state: Boolean, movieId: Int) {
        viewModel.updateFavoritesClick(state, movieId)
    }

    private fun movieOnClick(movieId: Int) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(NavPaths.concatenateMovieId(movieId).toUri())
            .build()
        findNavController().navigate(request)
    }

}