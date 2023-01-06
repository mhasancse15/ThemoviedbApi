package com.mahmudul.themoviedb_api.domain.repository

import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie.MovieResponse
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTopRatedMovies(apiKey: String, language: String, page: Int): Flow<Resource<MovieResponse>>
    fun getMovieDetails(apiKey: String): Flow<Resource<MovieDetailsResponse>>
}