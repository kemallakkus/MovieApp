package com.example.movieapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.extentions.handleLoadStates
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
                    loadState.handleLoadStates(progressBar, rvMovies) { errorMessage ->
                        AlertDialog.Builder(requireContext())
                            .setTitle("Error")
                            .setMessage(errorMessage.toString())
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }
                }
            }
        }
    }

    private fun onMovieClick(id: Int) {
        viewModel.setEvent(HomeEvent.MovieClicked(id))
    }

    override fun onDestroyView() {
        binding.rvMovies.adapter = null
        super.onDestroyView()
    }
}
