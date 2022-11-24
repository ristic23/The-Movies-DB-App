package com.example.movies_details_presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.repository_remote.IRepositoryMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val repositoryMovies: IRepositoryMovies

) : ViewModel()
{

}