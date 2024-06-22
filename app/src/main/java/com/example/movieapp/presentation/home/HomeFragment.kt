package com.example.movieapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.movieapp.R
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.extentions.gone
import com.example.movieapp.common.extentions.visible
import com.example.movieapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val homeAdapter by lazy {
        HomeAdapter(::onMovieClick)
    }

    override fun setupViews() {
        with(binding) {
            rvMovies.adapter = homeAdapter
        }

        collectLoadState()
    }

    override suspend fun collectStateInScope() {
        viewModel.state.collectLatest { pagingData ->
            homeAdapter.submitData(pagingData.movies)
        }
    }

    override suspend fun collectEffectInScope() {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeEffect.GoToDetail -> {
                    val bundle = Bundle().apply {
                        putInt("id", effect.id)
                    }
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
                }
            }
        }
    }

    private fun collectLoadState() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeAdapter.loadStateFlow.collect { loadState ->
                with(binding) {
                    when (loadState.refresh) {
                        is LoadState.Loading -> {
                            rvMovies.gone()
                            progressBar.visible()
                        }

                        is LoadState.NotLoading -> {
                            rvMovies.visible()
                            progressBar.gone()
                        }

                        is LoadState.Error -> {
                            progressBar.gone()
                            val errorState = loadState.refresh as LoadState.Error
                            Toast.makeText(requireContext(), "Error: ${errorState.error.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun onMovieClick(id: Int) {
        viewModel.setEvent(HomeEvent.MovieClicked(id))
    }
}
