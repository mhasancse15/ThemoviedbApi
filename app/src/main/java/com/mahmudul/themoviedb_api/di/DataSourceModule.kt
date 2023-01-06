package com.mahmudul.themoviedb_api.di

import com.mahmudul.themoviedb_api.data.source.RemoteDateSourceImpl
import com.mahmudul.themoviedb_api.data.source.MovieService
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDateSource(remoteService: MovieService): RemoteDataSource =
        RemoteDateSourceImpl(remoteService)
}