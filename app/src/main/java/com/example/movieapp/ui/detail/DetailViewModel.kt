package com.example.movieapp.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.base.BaseViewModel
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.usecases.MoviesUsesCases
import com.example.movieapp.common.util.Constants.EMPTY_STRING
import com.example.movieapp.common.util.Resource
import com.example.movieapp.data.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesUsesCases: MoviesUsesCases,
    saveStateHandle: SavedStateHandle,
) : BaseViewModel<DetailEvent, DetailState, DetailEffect>() {

    override fun setInitialState() = DetailState(isLoading = false)

    override fun handleEvents(event: DetailEvent) {
        when (event) {
            DetailEvent.BackClicked -> {
                setEffect {
                    DetailEffect.GoToBack
                }
            }
        }
    }

    init {
        saveStateHandle.get<Int>("id")?.let {
            getDetail(it)
        }
    }

    private fun getDetail(id: Int) {
        viewModelScope.launch {
            moviesUsesCases.getDetail(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        setState { copy(detail = resource.data) }
                    }

                    is Resource.Error -> {
                        setEffect { DetailEffect.ShowError(resource.error) }
                    }
                }
//            val detail = moviesUsesCases.getDetail(id)
//            setState { copy(detail = detail) }
            }
        }
    }
}

data class DetailState(
    val detail: Detail = Detail(),
    val isLoading: Boolean = false,
    val error: String = EMPTY_STRING,
)

sealed interface DetailEvent {
    data object BackClicked : DetailEvent
}

sealed interface DetailEffect {
    data object GoToBack : DetailEffect
    data class ShowError(val message: String) : DetailEffect
}
