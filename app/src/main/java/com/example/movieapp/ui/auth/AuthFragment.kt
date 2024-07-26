package com.example.movieapp.ui.auth

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.data.dto.request.LoginRequest
import com.example.movieapp.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()

    override fun setupViews() {
        with(binding) {
            loginButton.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                viewModel.setEvent(
                    AuthEvent.ValidateToken(
                        LoginRequest(
                            username = username,
                            password = password,
                            requestToken = viewModel.state.value.requestToken?.requestToken ?: "",
                        )
                    )
                )
            }
        }
//        val requestToken = arguments?.getString("request_token")
//        requestToken?.let {
//            viewModel.setEvent(AuthEvent.CreateSession(it))
//        }

        collectEffect()
    }

    private fun collectEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is AuthEffect.ShowError -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is AuthEffect.NavigateToHome -> {
                        findNavController().navigate(R.id.action_authFragment_to_homeFragment)
                    }
                }
            }
        }
    }
}

