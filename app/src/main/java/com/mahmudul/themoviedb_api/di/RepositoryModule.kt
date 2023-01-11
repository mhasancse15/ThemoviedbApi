package com.mahmudul.themoviedb_api.di

import com.mahmudul.themoviedb_api.data.repository.MovieRepositoryImpl
import com.mahmudul.themoviedb_api.data.source.MovieService
import com.mahmudul.themoviedb_api.domain.repository.MovieRepository
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDallERepository(
        remoteDataSource: RemoteDataSource,
        movieService: MovieService
    ): MovieRepository =
        MovieRepositoryImpl(remoteDataSource, movieService)
}