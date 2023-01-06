package com.mahmudul.themoviedb_api.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.mahmudul.themoviedb_api.data.model.movie.Movie
import com.mahmudul.themoviedb_api.data.model.movie.MovieResponse
import com.mahmudul.themoviedb_api.domain.source.RemoteDataSource

class MoviePagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val apiKey: String,
    private val language: String,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, Movie> {
        return try {

            val pageNumber = params.key ?: 1 // current page that is being displayed
            val response: MovieResponse = remoteDataSource.getTopRatedMovies(
                apiKey,
                language,
                pageNumber

            )

            val data: ArrayList<Movie> = response.results as ArrayList<Movie> // List of results from the API

            PagingSource.LoadResult.Page(
                data = data, // Provide the List<CharacterDTO>
                prevKey = null,
                nextKey = if (data.isEmpty()) null else pageNumber + 1 // As the user scrolls provide the next page number till there is no more data
            )
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}