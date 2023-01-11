package com.mahmudul.themoviedb_api.data.source

import com.mahmudul.themoviedb_api.data.source.dto.MovieResultDto
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    //GET Top Rated Movies
    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResultDto

    //GET Movie Details
    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String,
    ): MovieDetailsResponse
}