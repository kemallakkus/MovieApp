package com.example.movieapp.ui.profile

import androidx.lifecycle.viewModelScope
import com.example.movieapp.common.base.BaseViewModel
import com.example.movieapp.common.util.Resource
import com.example.movieapp.domain.model.Account
import com.example.movieapp.domain.usecases.GetMeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getMeUseCase: GetMeUseCase,
) : BaseViewModel<ProfileEvent, ProfileState, ProfileEffect>() {

    override fun setInitialState() = ProfileState(isLoading = false)

    override fun handleEvents(event: ProfileEvent) {
        TODO("Not yet implemented")
    }

    init {
        getMe(16450348)
    }

    private fun getMe(accountId: Int) {
        viewModelScope.launch {
            getMeUseCase(accountId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        setState { copy(account = resource.data) }
                    }

                    is Resource.Error -> {
                        setEffect { ProfileEffect.ShowError(resource.error) }
                    }
                }
            }
        }
    }
}

data class ProfileState(
    val account: Account? = null,
    val isLoading: Boolean = false,
)

sealed interface ProfileEvent {
    data object BackClicked : ProfileEvent
}

sealed interface ProfileEffect {
    data object GoToBack : ProfileEffect
    data class ShowError(val message: String) : ProfileEffect
}