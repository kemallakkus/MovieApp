package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import com.example.movieapp.domain.usecases.MoviesUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUsesCases: MoviesUsesCases
) : ViewModel() {

    private val _homePaging = MutableStateFlow(PagingData.empty<Movie>())
    val homePaging: StateFlow<PagingData<Movie>> = _homePaging

    init {
        getMovies()
    }

    private fun getMovies() {
        moviesUsesCases.getMovies.invoke().cachedIn(viewModelScope).onEach { pagingData ->
            _homePaging.update { pagingData }
        }.launchIn(viewModelScope)
    }
}