package com.example.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.domain.model.Movie
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
    private val moviesUsesCases: MoviesUsesCases,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState

    init {
        getMovies()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.MovieClicked -> {
//                _homeState.update {
//                    it.copy(effect = HomeEffect.GoToDetail(event.id)) }
            }
        }
    }

    private fun getMovies() {
        _homeState.update { it.copy(isLoading = true) }

        moviesUsesCases.getMovies.invoke().cachedIn(viewModelScope).onEach { pagingData ->
            _homeState.update { it.copy(movies = pagingData, isLoading = false) }
        }.launchIn(viewModelScope)
    }
}

sealed interface HomeEvent {
    data class MovieClicked(val id: Int) : HomeEvent
}

sealed interface HomeEffect {
    data class GoToDetail(val id: Int) : HomeEffect
}

data class HomeState(
    val movies: PagingData<Movie> = PagingData.empty(),
    val isLoading: Boolean = false,
    val error: String = ""
)