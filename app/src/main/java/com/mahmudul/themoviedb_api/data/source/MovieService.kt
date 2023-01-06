package com.mahmudul.themoviedb_api.data.source

import com.mahmudul.themoviedb_api.data.model.movie.MovieResponse
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    //GET Top Rated Movies
    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MovieResponse

    //GET Movie Details
    @GET("3/movie/550")
    suspend fun getMovieDetails(
        @Query("api_key") apiKey: String
    ): MovieDetailsResponse
}