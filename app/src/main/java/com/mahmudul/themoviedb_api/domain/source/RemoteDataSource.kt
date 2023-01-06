package com.mahmudul.themoviedb_api.domain.source

import com.mahmudul.themoviedb_api.data.model.movie.MovieResponse
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse

interface RemoteDataSource {
    suspend fun getTopRatedMovies(apiKey: String, language: String, page: Int): MovieResponse
    suspend fun getMovieDetails(movieId: String, apiKey: String): MovieDetailsResponse
}