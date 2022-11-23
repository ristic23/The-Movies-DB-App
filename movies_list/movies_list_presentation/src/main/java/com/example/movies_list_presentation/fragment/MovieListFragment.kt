package com.example.movies_list_presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.core.navigation.NavPaths.concatenateMovieId
import com.example.core.navigation.NavPaths.moviesDetailsPath
import com.example.movies_list_presentation.databinding.FragmentMoviesListBinding
import com.example.movies_list_presentation.viewModel.MoviesListViewModel

class MovieListFragment: Fragment() {
    private lateinit var historyViewModel: MoviesListViewModel

    private lateinit var binding: FragmentMoviesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_birthday_moon, null, false)
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        binding.btnGoto.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
//                .fromUri(moviesDetailsPath.toUri())
                .fromUri(concatenateMovieId(44).toUri())
                .build()
            findNavController().navigate(request)
        }

        return binding.root
    }

}