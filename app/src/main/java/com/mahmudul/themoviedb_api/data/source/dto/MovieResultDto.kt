package com.mahmudul.themoviedb_api.data.source.dto

data class MovieResultDto(
    val page: Int?,
    val results: List<MovieDto>?,
    val total_pages: Int?,
    val total_results: Int?
)