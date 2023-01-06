package com.mahmudul.themoviedb_api.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import com.mahmudul.themoviedb_api.domain.use_case.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<Resource<MovieDetailsResponse>?>(null)
    val state = _state.asStateFlow()

    fun getMovieDetails(movieId: String, apiKey: String) = viewModelScope.launch {
        movieUseCase(movieId, apiKey).collect {
            _state.emit(it)
        }
    }
}

