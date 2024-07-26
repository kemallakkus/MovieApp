package com.example.movieapp.ui.auth

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.base.BaseViewModel
import com.example.movieapp.common.util.Resource
import com.example.movieapp.data.dto.request.LoginRequest
import com.example.movieapp.domain.model.Session
import com.example.movieapp.domain.model.Token
import com.example.movieapp.domain.model.ValidateToken
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecases.CreateRequestTokenUseCase
import com.example.movieapp.domain.usecases.CreateSessionUseCase
import com.example.movieapp.domain.usecases.ValidateTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val createRequestTokenUseCase: CreateRequestTokenUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val validateTokenUseCase: ValidateTokenUseCase,
    private val sharedPreferences: SharedPreferences,
) : BaseViewModel<AuthEvent, AuthState, AuthEffect>() {

    override fun setInitialState() = AuthState()

    override fun handleEvents(event: AuthEvent) {
        when (event) {
            is AuthEvent.ValidateToken -> validateToken(
                LoginRequest(
                    event.loginRequest.username,
                    event.loginRequest.password,
                    event.loginRequest.requestToken
                )
            )
        }
    }

    init {
        createRequestToken()
    }

    private fun createRequestToken() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            createRequestTokenUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        setState {
                            copy(
                                isLoading = false,
                                requestToken = resource.data
                            )
                        }
                    }

                    is Resource.Error -> {
                        setState { copy(isLoading = false) }
                        setEffect { AuthEffect.ShowError(resource.error) }
                    }
                }
            }
        }
    }

    private fun validateToken(loginRequest: LoginRequest) {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            validateTokenUseCase(loginRequest).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val token = resource.data.requestToken
                        setState {
                            copy(
                                isLoading = false,
                                validateToken = resource.data
                            )
                        }
                        createSession(token)
                    }

                    is Resource.Error -> {
                        setState { copy(isLoading = false) }
                        setEffect { AuthEffect.ShowError(resource.error) }
                    }
                }
            }
        }
    }

    private fun createSession(requestToken: String) {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            createSessionUseCase(requestToken).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val sessionId = resource.data.sessionId
                        saveSessionId(sessionId)
                        setState {
                            copy(
                                isLoading = false,
                                sessionId = resource.data
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
                        setEffect { AuthEffect.ShowError(resource.error) }
                    }
                }
            }

        }
    }

    private fun saveSessionId(sessionId: String) {
        sharedPreferences.edit().putString("SESSION_ID", sessionId).apply()
    }
}

sealed interface AuthEvent {
    data class ValidateToken(val loginRequest: LoginRequest) : AuthEvent
}

sealed interface AuthEffect {
    data class ShowError(val message: String) : AuthEffect
    data class NavigateToHome(val sessionId: String) : AuthEffect
}

data class AuthState(
    val isLoading: Boolean = false,
    val requestToken: Token? = null,
    val sessionId: Session? = null,
    val validateToken: ValidateToken? = null,
)
