package com.example.movies_list_presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.navigation.NavPaths.concatenateMovieId
import com.example.core.navigation.NavPaths.moviesFavoritesPath
import com.example.core.navigation.NavPaths.moviesSearchPath
import com.example.movies_list_presentation.R
import com.example.movies_list_presentation.adapter.MoviesAdapter
import com.example.movies_list_presentation.databinding.FragmentMoviesListBinding
import com.example.movies_list_presentation.viewModel.MoviesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment: Fragment() {

    private val viewModel: MoviesListViewModel by viewModels()

    private lateinit var binding: FragmentMoviesListBinding

    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var gridManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_list, null, false)

        with(binding) {
            moviesListViewModel = viewModel

            lifecycleOwner = this@MovieListFragment

            return root
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter(
            myDataSet = mutableListOf(),
            movieOnClick = ::movieOnClick
        )

        gridManager = GridLayoutManager(activity as Context, 1)
        binding.movies.apply {
            adapter = moviesAdapter
            layoutManager = gridManager
        }

        viewModel.moviesResultLiveData.observe(viewLifecycleOwner) {
            moviesAdapter.updateList(it.retrofitMovies)
        }

        binding.search.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri(moviesSearchPath.toUri())
                .build()
            findNavController().navigate(request)
        }
        binding.favorites.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri(moviesFavoritesPath.toUri())
                .build()
            findNavController().navigate(request)
        }

        binding.linearGridBtn.setOnClickListener {
            if (binding.linearGridBtn.isChecked) {
                gridManager.spanCount = 2
            } else {
                gridManager.spanCount = 1
            }
        }
    }

    private fun movieOnClick(movieId: Int) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(concatenateMovieId(movieId).toUri())
            .build()
        findNavController().navigate(request)
    }

}