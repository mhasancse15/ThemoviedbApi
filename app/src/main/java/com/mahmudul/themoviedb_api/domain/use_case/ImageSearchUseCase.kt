package com.mahmudul.themoviedb_api.domain.use_case

import com.mahmudul.themoviedb_api.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(apiKey: String, language: String) =
        repository.getTopRatedMovies(apiKey, language)

    /*operator fun invoke(movieId: String, apiKey: String) =
        repository.getMovieDetails(movieId, apiKey)*/
}