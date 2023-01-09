package com.mahmudul.themoviedb_api.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie.Movie
import com.mahmudul.themoviedb_api.data.model.movie.MovieResponse
import com.mahmudul.themoviedb_api.domain.use_case.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<Movie>?>(null)
    val state = _state.asStateFlow()

    fun getTopRatedMovies(apiKey: String, language: String) = viewModelScope.launch {

        movieUseCase(apiKey, language).collect {
            _state.emit(it)
        }
    }
}