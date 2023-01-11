package com.mahmudul.themoviedb_api.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mahmudul.themoviedb_api.common.Constants
import com.mahmudul.themoviedb_api.data.mapper.toDomain
import com.mahmudul.themoviedb_api.data.source.MovieService
import com.mahmudul.themoviedb_api.domain.model.Movie

class MoviesPagingSource(
    private val apiService: MovieService,
    private val apiKey: String,
    private val language: String
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: Constants.STARTING_PAGE_INDEX
        return try {
            val response =
                apiService.getTopRatedMovies(
                    apiKey,
                    language,
                    position
                )
            val movieList = response.results
            LoadResult.Page(
                data = movieList!!.map { it.toDomain() },
                prevKey = if (position == Constants.STARTING_PAGE_INDEX) null else position - Constants.DEFAULT_LIMIT,
                nextKey = if (movieList.isEmpty()) null else (position) + Constants.DEFAULT_LIMIT
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}