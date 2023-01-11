package com.mahmudul.themoviedb_api.domain.repository

import androidx.paging.PagingData
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import com.mahmudul.themoviedb_api.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieDetails(movieId: String, apiKey: String): Flow<Resource<MovieDetailsResponse>>
    fun getMovieList(apiKey: String, language: String): Flow<PagingData<Movie>>
}