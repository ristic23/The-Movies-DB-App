package com.example.movies_details_presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.movies_details_presentation.databinding.FragmentMoviesDetailsBinding
import com.example.movies_details_presentation.viewModel.MoviesDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment: Fragment() {
    private lateinit var historyViewModel: MoviesDetailsViewModel

    private lateinit var binding: FragmentMoviesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_birthday_moon, null, false)
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        val argument = arguments?.getInt("movieId")

        binding.btnGoto2.text = argument.toString()

        return binding.root
    }


}