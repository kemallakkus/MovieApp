package com.example.movieapp.ui.auth

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: AuthViewModel by viewModels()

    override fun setupViews() {
        binding.loginButton.setOnClickListener {
            viewModel.setEvent(AuthEvent.CreateRequestToken)
        }

        val requestToken = arguments?.getString("request_token")
        requestToken?.let {
            viewModel.setEvent(AuthEvent.CreateSession(it))
        }

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

                    is AuthEffect.OpenAuthUrl -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(effect.url))
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

