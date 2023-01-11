package com.mahmudul.themoviedb_api.data.mapper

import com.mahmudul.themoviedb_api.data.source.dto.MovieDto
import com.mahmudul.themoviedb_api.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdrop_path = backdrop_path,
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count
    )
}