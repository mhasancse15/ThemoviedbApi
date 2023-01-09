package com.mahmudul.themoviedb_api.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie.Movie
import com.mahmudul.themoviedb_api.domain.paging.MoviePagingSource
import com.mahmudul.themoviedb_api.domain.repository.MovieRepository
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override fun getTopRatedMovies(
        apiKey: String,
        language: String):Flow<PagingData<Movie>>{
        return Pager(
            config = PagingConfig(
                10,
                6,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {MoviePagingSource(remoteDataSource, apiKey,language)}
        ).flow
    }


    /* = flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.getTopRatedMovies(apiKey, language, page)
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }*/

    /*    override fun queryImage(
        query: String,
        apiKey: String,
        imageType: String
    ): Flow<PagingData<Hit>> {
        return Pager(
            config = PagingConfig(
                10,
                6,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RemoteDateSourceImpl(
                    imageSearchService,
                    query,
                    apiKey,
                    imageType
                )
            }

        ).flow

        /*= flow {
        emit(Resource.Loading)
        try {
            val response = remoteDataSource.queryImage(query, apiKey, imageType)
            emit(Resource.Success(response))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        }
    }*/

    }
}*/

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