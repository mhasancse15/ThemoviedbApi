package com.mahmudul.themoviedb_api.data.source

import com.mahmudul.themoviedb_api.data.model.movie.MovieResponse
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource


class RemoteDateSourceImpl(private val remoteService: MovieService) : RemoteDataSource {

    override suspend fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ): MovieResponse {
        return remoteService.getTopRatedMovies(apiKey, language, page)
    }

    override suspend fun getMovieDetails(movieId: String, apiKey: String): MovieDetailsResponse {
        return remoteService.getMovieDetails(movieId, apiKey)
    }
}