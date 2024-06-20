package com.example.movieapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.dto.DetailDto
import com.example.movieapp.domain.model.Detail
import com.example.movieapp.domain.usecases.MoviesUsesCases
import com.example.movieapp.presentation.home.HomeState
import com.example.movieapp.util.Constants.EMPTY_STRING
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesUsesCases: MoviesUsesCases,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState

    init {
        saveStateHandle.get<Int>("id")?.let {
            getDetail(it)
        }
    }

    fun getDetail(id: Int) {
        viewModelScope.launch {
            val detail = moviesUsesCases.getDetail(id)
            _detailState.value = DetailState(detail = detail)
        }
    }
}


data class DetailState(
    val detail: Detail = Detail(),
    val isLoading: Boolean = false,
    val error: String = EMPTY_STRING,
)
