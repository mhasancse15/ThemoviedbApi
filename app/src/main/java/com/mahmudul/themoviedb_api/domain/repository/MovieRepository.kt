package com.mahmudul.themoviedb_api.domain.repository

import androidx.paging.PagingData
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie.Movie
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTopRatedMovies(apiKey: String, language: String): Flow<PagingData<Movie>>
    fun getMovieDetails(movieId: String, apiKey: String): Flow<Resource<MovieDetailsResponse>>
}