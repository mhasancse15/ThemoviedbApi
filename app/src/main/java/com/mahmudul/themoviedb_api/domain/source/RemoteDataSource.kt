package com.mahmudul.themoviedb_api.domain.source

import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse

interface RemoteDataSource {
    suspend fun getMovieDetails(movieId: String, apiKey: String): MovieDetailsResponse
}