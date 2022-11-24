package com.example.movies_list_presentation.viewModel

import com.example.repository_remote.IRepositoryMovies
import com.example.repository_remote.RepositoryMovies
import javax.inject.Inject

class Temp @Inject constructor(
    private val repositoryMovies: IRepositoryMovies
) {
}