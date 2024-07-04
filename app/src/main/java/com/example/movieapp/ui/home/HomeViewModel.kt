package com.example.movieapp.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.common.base.BaseViewModel
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.common.util.Constants.EMPTY_STRING
import com.example.movieapp.common.util.Resource
import com.example.movieapp.domain.model.Genre
import com.example.movieapp.domain.usecases.GetGenresUseCase
import com.example.movieapp.domain.usecases.GetMoviesUseCase
import com.example.movieapp.ui.detail.DetailEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase
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
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        setState {
                            copy(genres = resource.data)
                        }
                    }

                    is Resource.Error -> {
                        setEffect { HomeEffect.ShowError(resource.error) }
                    }
                }
            }
        }
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
    data class ShowError(val message: String) : HomeEffect
}

data class HomeState(
    val movies: PagingData<Movie> = PagingData.empty(),
    val genres: List<Genre> = emptyList(),
    val isLoading: Boolean = false
)