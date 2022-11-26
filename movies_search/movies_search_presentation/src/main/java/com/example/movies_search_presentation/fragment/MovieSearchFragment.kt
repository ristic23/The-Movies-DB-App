package com.example.movies_search_presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.navigation.NavPaths
import com.example.movies_search_presentation.R
import com.example.movies_search_presentation.databinding.FragmentMoviesSearchBinding
import com.example.movies_search_presentation.viewModel.MovieSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieSearchFragment: Fragment() {

    private lateinit var binding: FragmentMoviesSearchBinding
    private val viewModel: MovieSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesFavoritesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies_search, null, false)

        with(binding) {
            moviesSearchViewModel = viewModel

            moviesResult.setContent {
                MovieSearchScreen(
                    modifier = Modifier.fillMaxSize(),
                    moviesSearchViewModel = viewModel,
                    movieClick = {
                        val request = NavDeepLinkRequest.Builder
                            .fromUri(NavPaths.concatenateMovieId(it).toUri())
                            .build()
                        findNavController().navigate(request)
                    }
                )
            }

            lifecycleOwner = this@MovieSearchFragment

            return root
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.startSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.
                viewModel.updateCurrentQuery(newText ?: "")
                return false
            }
        })
    }

}