package com.mahmudul.themoviedb_api.presentation.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mahmudul.themoviedb_api.common.Constants
import com.mahmudul.themoviedb_api.domain.model.Movie
import com.mahmudul.themoviedb_api.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    var wrapperType: String = Constants.DEFAULT_WRAPPER_TYPE
    private val _movieList: MutableLiveData<PagingData<Movie>> = MutableLiveData(PagingData.empty())
    val movieList = _movieList
    fun getSearchApi(apiKey: String, language: String) {
        viewModelScope.launch {
            movieRepository.getMovieList(apiKey, language).distinctUntilChanged()
                .collectLatest {
                    _movieList.value = it
                }
        }
    }

}