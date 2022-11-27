package com.example.repository_remote.mapper

import com.example.core.dtoMovies.Movie
import com.example.core.dtoMovies.Movies
import com.example.core.dtoMovies.castAndCrew.MovieCast
import com.example.core.dtoMovies.castAndCrew.MovieCastAndCrew
import com.example.core.dtoMovies.castAndCrew.MovieCrew
import com.example.core.dtoMovies.detailMovie.DetailsMovie
import com.example.core.dtoMovies.detailMovie.MovieGenre
import com.example.core.dtoMovies.favorite.MovieFavorite
import com.example.core.dtoMovies.recommendations.RecommendationMovie
import com.example.core.dtoMovies.recommendations.RecommendationMovies
import com.example.retrofit.dto.RetrofitMovie
import com.example.retrofit.dto.RetrofitMovies
import com.example.retrofit.dto.crewAndCast.Cast
import com.example.retrofit.dto.crewAndCast.Crew
import com.example.retrofit.dto.crewAndCast.RetrofitCastAndCrew
import com.example.retrofit.dto.detailMovie.RetrofitDetailsMovie
import com.example.retrofit.dto.movieRecommendations.RetrofitMovieRecommendation
import com.example.retrofit.dto.movieRecommendations.RetrofitRecommendationResult
import com.example.roomdb.entities.FavoriteEntity
import com.example.roomdb.entities.MovieEntity

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

fun retrofitMovieEntityToMovie(movieEntity: MovieEntity?): Movie = Movie(
    backdrop_path = movieEntity?.backdrop_path ?: "",
    genre_ids = listOf(),
    id = movieEntity?.id ?: 0,
    original_language = movieEntity?.original_language ?: "",
    overview = movieEntity?.overview ?: "",
    popularity = movieEntity?.popularity ?: 0.0,
    poster_path = movieEntity?.poster_path ?: "",
    release_date = movieEntity?.release_date ?: "",
    title = movieEntity?.title ?: "",
    vote_average = movieEntity?.vote_average ?: 0.0,
    vote_count = movieEntity?.vote_count ?: 0
)

fun retrofitMovieToMovieEntity(retrofitMovie: RetrofitMovie?): MovieEntity = MovieEntity(
    backdrop_path = retrofitMovie?.backdrop_path ?: "",
    id = retrofitMovie?.id ?: 0,
    original_language = retrofitMovie?.original_language ?: "",
    overview = retrofitMovie?.overview ?: "",
    popularity = retrofitMovie?.popularity ?: 0.0,
    poster_path = retrofitMovie?.poster_path ?: "",
    release_date = retrofitMovie?.release_date ?: "",
    title = retrofitMovie?.title ?: "",
    vote_average = retrofitMovie?.vote_average ?: 0.0,
    vote_count = retrofitMovie?.vote_count ?: 0,
    adult = retrofitMovie?.adult ?: false,
    original_title = retrofitMovie?.original_title ?: "",
    video = retrofitMovie?.video ?: false,
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
    tagline = retrofitMovie?.tagline ?: ""

)

fun retrofitGenreToGenre(movieGenre: MovieGenre): MovieGenre = MovieGenre(
    id = movieGenre.id,
    name = movieGenre.name
)

fun retrofitCastAndCrewToMovieCastAndCrew(retrofitCastAndCrew: RetrofitCastAndCrew): MovieCastAndCrew =
    MovieCastAndCrew(
        cast = retrofitCastAndCrew.cast.map {
            retrofitCastToMovieCast(it)
        },
        crew = retrofitCastAndCrew.crew.map {
            retrofitCrewToMovieCrew(it)
        },
        id = retrofitCastAndCrew.id,
    )
fun retrofitCastToMovieCast(retrofitCast: Cast?): MovieCast =
    MovieCast(
        character = retrofitCast?.character ?: "",
        gender = retrofitCast?.gender ?: 0,
        id = retrofitCast?.id ?: 0,
        name = retrofitCast?.name ?: "",
        original_name = retrofitCast?.original_name ?: "",
        profile_path = retrofitCast?.profile_path ?: ""
    )
fun retrofitCrewToMovieCrew(retrofitCrew: Crew?): MovieCrew =
    MovieCrew(
        department = retrofitCrew?.department ?: "",
        gender = retrofitCrew?.gender ?: 0,
        job = retrofitCrew?.job ?: "",
        name = retrofitCrew?.name ?: "",
        original_name = retrofitCrew?.original_name ?: "",
        profile_path = retrofitCrew?.profile_path ?: ""
    )

fun retrofitRecommendedMoviesToRecommendedMovies(retrofitMovieRecommendation: RetrofitMovieRecommendation?): RecommendationMovies =
    RecommendationMovies(
        page = 1,
        results = retrofitMovieRecommendation?.results?.map {
            retrofitRecommendedMovieToRecommendedMovie(it)
        } ?: listOf(),
        total_pages = 1,
        total_results = 20
    )
fun retrofitRecommendedMovieToRecommendedMovie(retrofitRecommendationResult: RetrofitRecommendationResult?): RecommendationMovie =
    RecommendationMovie(
        id = retrofitRecommendationResult?.id ?: 0,
        poster_path = retrofitRecommendationResult?.poster_path ?: "",
        release_date = retrofitRecommendationResult?.release_date ?: "",
        title = retrofitRecommendationResult?.title ?: ""
    )

fun favoriteEntityToMovieFavorite(favorite: FavoriteEntity?): MovieFavorite =
    MovieFavorite(
        uid = favorite?.uid ?: 0,
        movie_id = favorite?.movieId ?: 0
    )

fun movieFavoriteToFavoriteEntity(favorite: MovieFavorite): FavoriteEntity =
    FavoriteEntity(
        uid = favorite.uid,
        movieId = favorite.movie_id
    )

