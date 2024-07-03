package com.example.movieapp.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

    override suspend fun collectStateInScope() {
        viewModel.state.collect { state ->
            // State güncellemelerini burada yönetin
        }
    }

    override suspend fun collectEffectInScope() {
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

//    override fun onResume() {
//        super.onResume()
//
//        val uri = requireActivity().intent?.data
//        uri?.let {
//            if (it.toString().startsWith("yourapp://auth")) {
//                val requestToken = it.getQueryParameter("request_token")
//                if (requestToken != null) {
//                    viewModel.setEvent(AuthEvent.CreateSession(requestToken))
//                }
//            }
//        }
//    }
}

