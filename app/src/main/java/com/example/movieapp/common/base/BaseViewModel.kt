package com.example.movieapp.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

interface Event
interface State
interface Effect

abstract class BaseViewModel<Event, State, Effect> : ViewModel() {

    private val initialState: State by lazy { setInitialState() }

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()

    init {
        subscribeToEvents()
    }

    abstract fun setInitialState(): State

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun setState(reducer: State.() -> State) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.emit(effectValue) }
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    abstract fun handleEvents(event: Event)
}