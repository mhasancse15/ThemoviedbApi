package com.mahmudul.themoviedb_api.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mahmudul.themoviedb_api.common.Constants
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.paging.MoviesPagingSource
import com.mahmudul.themoviedb_api.data.source.MovieService
import com.mahmudul.themoviedb_api.domain.model.Movie
import com.mahmudul.themoviedb_api.domain.repository.MovieRepository
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val apiService: MovieService,
) : MovieRepository {

    override fun getMovieDetails(movieId: String, apiKey: String) = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getMovieDetails(movieId, apiKey)
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }

    override fun getMovieList(apiKey: String, language: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.STARTING_PAGE_INDEX,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(apiService, apiKey, language) }
        ).flow
    }

}