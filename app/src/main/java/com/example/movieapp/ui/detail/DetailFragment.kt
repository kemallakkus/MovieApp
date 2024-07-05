package com.example.movieapp.ui.detail

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.extentions.loadImage
import com.example.movieapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()

    override fun setupViews() {
        with(binding) {
            ivBack.setOnClickListener {
                viewModel.setEvent(DetailEvent.BackClicked)
            }
        }
        collectEffect()
        collectState()
    }

    private fun collectState() {
        with(binding) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.state.collectLatest { state ->
                    ivMovie.loadImage(state.detail.posterPath)
                    tvMovieName.text = state.detail.name
                    tvMovieOverview.text = state.detail.overview
                    rbMovieRate.rating = state.detail.rating
                }
            }
        }
    }

    private fun collectEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.effect.collectLatest { effect ->
                when (effect) {
                    is DetailEffect.GoToBack -> {
                        findNavController().navigateUp()
                    }

                    is DetailEffect.ShowError -> {
                        AlertDialog.Builder(requireContext())
                            .setTitle("Error")
                            .setMessage(effect.message)
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()

                    }
                }
            }
        }
    }
}