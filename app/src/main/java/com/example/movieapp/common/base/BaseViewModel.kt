package com.example.movieapp.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


/**
 * BaseViewModel sınıfı, MVVM (Model-View-ViewModel) mimarisine uygun olarak
 * olaylar (events), durumlar (states) ve etkiler (effects) arasında iletişimi
 * yönetmek için tasarlanmış bir temel ViewModel sınıfıdır. Bu sınıf, olayları
 * yönetir ve UI durumunu günceller.
 *
 * @param Event : UI bileşenlerinden gelen olayları temsil eder.
 * @param State : Uygulamanın mevcut durumunu temsil eder.
 * @param Effect : UI üzerinde meydana gelen yan etkileri temsil eder.
 *
 * Yöntemler:
 * - setInitialState: Alt sınıflar tarafından başlangıç durumunu belirlemek için
 *   uygulanması gereken soyut bir yöntemdir.
 * - setEvent: Yeni bir olay belirler ve onu _event akışına yayar.
 * - setState: Mevcut durumu bir indirgeme (reducer) fonksiyonu kullanarak günceller.
 * - setEffect: Yeni bir etki belirler ve onu _effect akışına yayar.
 * - subscribeToEvents: Olayları toplar ve handleEvents yöntemi ile işler.
 * - handleEvents: Alt sınıflar tarafından uygulanması gereken ve olayları işlemek
 *   için kullanılan soyut bir yöntemdir.
 *
 * Bu sınıf, ViewModel'in yaşam döngüsüne duyarlı olarak çalışır ve coroutine'ler
 * kullanarak asenkron veri akışlarını yönetir.
 */
abstract class BaseViewModel<Event, State, Effect> : ViewModel() {

    private val initialState: State by lazy { setInitialState() }

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> = _event.asSharedFlow()

    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect: SharedFlow<Effect> = _effect.asSharedFlow()

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