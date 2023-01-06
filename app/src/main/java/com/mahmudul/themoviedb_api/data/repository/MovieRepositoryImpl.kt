package com.mahmudul.themoviedb_api.data.repository

import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.domain.repository.MovieRepository
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override fun getTopRatedMovies(
        apiKey: String,
        language: String,
        page: Int
    ) = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getTopRatedMovies(apiKey, language, page)
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getMovieDetails(movieId: String, apiKey: String) = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getMovieDetails(movieId, apiKey)
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

}