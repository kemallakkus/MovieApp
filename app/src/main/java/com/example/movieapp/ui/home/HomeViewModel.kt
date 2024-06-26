package com.example.movieapp.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.common.base.BaseViewModel
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.common.util.Constants.EMPTY_STRING
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : BaseViewModel<HomeEvent, HomeState, HomeEffect>() {

    override fun setInitialState() = HomeState(isLoading = false)

    override fun handleEvents(event: HomeEvent) {
        when (event) {
            is HomeEvent.MovieClicked -> {
                setEffect {
                    HomeEffect.GoToDetail(event.id)
                }
            }
        }
    }

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        getMoviesUseCase().cachedIn(viewModelScope).collectLatest {
            setState {
                copy(movies = it)
            }
        }
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
    val isLoading: Boolean = false
)