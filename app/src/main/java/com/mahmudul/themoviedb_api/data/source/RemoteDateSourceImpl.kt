package com.mahmudul.themoviedb_api.data.source
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource


class RemoteDateSourceImpl(private val remoteService: MovieService) : RemoteDataSource {

    override suspend fun getMovieDetails(movieId: String, apiKey: String): MovieDetailsResponse {
        return remoteService.getMovieDetails(movieId, apiKey)
    }
}