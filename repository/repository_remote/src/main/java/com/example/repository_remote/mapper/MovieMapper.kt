package com.example.repository_remote.mapper

import com.example.core.dtoMovies.Movie
import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core.dtoMovies.detailMovie.MovieGenre
import com.example.retrofit.dto.RetrofitMovie
import com.example.retrofit.dto.RetrofitMovies
import com.example.retrofit.dto.detailMovie.RetrofitDetailsMovie

fun retrofitMoviesToMovies(retrofitMovies: RetrofitMovies):Movies = Movies(
    page = retrofitMovies.page,
    retrofitMovies = retrofitMovies.retrofitMovies
        .map {
            retrofitMovieToMovie(it)
        },
    total_pages = retrofitMovies.total_pages,
    total_results = retrofitMovies.total_results
)

fun retrofitMovieToMovie(retrofitMovie: RetrofitMovie?): Movie = Movie(
    backdrop_path = retrofitMovie?.backdrop_path ?: "",
    genre_ids = retrofitMovie?.genre_ids ?: listOf(),
    id = retrofitMovie?.id ?: 0,
    original_language = retrofitMovie?.original_language ?: "",
    overview = retrofitMovie?.overview ?: "",
    popularity = retrofitMovie?.popularity ?: 0.0,
    poster_path = retrofitMovie?.poster_path ?: "",
    release_date = retrofitMovie?.release_date ?: "",
    title = retrofitMovie?.title ?: "",
    vote_average = retrofitMovie?.vote_average ?: 0.0,
    vote_count = retrofitMovie?.vote_count ?: 0
)

fun retrofitMovieDetailToMovieDetail(retrofitMovie: RetrofitDetailsMovie?): DetailsMovie = DetailsMovie(
    backdrop_path = retrofitMovie?.backdrop_path ?: "",
    genres = retrofitMovie?.genres?.map {
        retrofitGenreToGenre(it)
        } ?: listOf(),
    id = retrofitMovie?.id ?: 0,
    original_language = retrofitMovie?.original_language ?: "",
    overview = retrofitMovie?.overview ?: "",
    popularity = retrofitMovie?.popularity ?: 0.0,
    poster_path = retrofitMovie?.poster_path ?: "",
    release_date = retrofitMovie?.release_date ?: "",
    title = retrofitMovie?.title ?: "",
    vote_average = retrofitMovie?.vote_average ?: 0.0,
    vote_count = retrofitMovie?.vote_count ?: 0,
    budget = retrofitMovie?.budget ?: 0,
    original_title = retrofitMovie?.original_title ?: "",
    revenue = retrofitMovie?.revenue ?: 0,
    runtime = retrofitMovie?.runtime ?: 0,
    status = retrofitMovie?.status ?: "",
    tagline = retrofitMovie?.tagline ?: "",

)

fun retrofitGenreToGenre(movieGenre: MovieGenre): MovieGenre = MovieGenre(
    id = movieGenre.id,
    name = movieGenre.name
)

