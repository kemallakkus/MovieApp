package com.example.movieapp.ui.auth

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.base.BaseViewModel
import com.example.movieapp.common.util.Resource
import com.example.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel<AuthEvent, AuthState, AuthEffect>() {

    override fun setInitialState() = AuthState()

    override fun handleEvents(event: AuthEvent) {
        when (event) {
            is AuthEvent.CreateRequestToken -> createRequestToken()
            is AuthEvent.CreateSession -> createSession(event.requestToken)
        }
    }

    private fun createRequestToken() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = movieRepository.createRequestToken()) {
                is Resource.Success -> {
                    val requestToken = result.data.requestToken
                    val authUrl = "https://www.themoviedb.org/authenticate/$requestToken?redirect_to=movieapp://auth"
                    setState {
                        copy(
                            isLoading = false,
                            requestToken = requestToken
                        )
                    }
                    setEffect {
                        AuthEffect.OpenAuthUrl(
                            authUrl
                        )
                    }
                }

                is Resource.Error -> {
                    setState { copy(isLoading = false) }
                    setEffect { AuthEffect.ShowError(result.error) }
                }
            }
        }
    }

    private fun createSession(requestToken: String) {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = movieRepository.createSession(requestToken)) {
                is Resource.Success -> {
                    val sessionId = result.data.sessionId
                    saveSessionId(sessionId)
                    setState {
                        copy(
                            isLoading = false,
                            sessionId = sessionId
                        )
                    }
                    setEffect {
                        AuthEffect.NavigateToHome(
                            sessionId
                        )
                    }
                }

                is Resource.Error -> {
                    setState { copy(isLoading = false) }
                    setEffect { AuthEffect.ShowError(result.error) }
                }
            }
        }
    }

    private fun saveSessionId(sessionId: String) {
        sharedPreferences.edit().putString("SESSION_ID", sessionId).apply()
    }
}

sealed interface AuthEvent {
    data object CreateRequestToken : AuthEvent
    data class CreateSession(val requestToken: String) : AuthEvent
}

sealed interface AuthEffect {
    data class ShowError(val message: String) : AuthEffect
    data class NavigateToHome(val sessionId: String) : AuthEffect
    data class OpenAuthUrl(val url: String) : AuthEffect
}

data class AuthState(
    val isLoading: Boolean = false,
    val requestToken: String? = null,
    val sessionId: String? = null,
)
