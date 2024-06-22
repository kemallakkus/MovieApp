package com.example.movieapp.ui.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieapp.common.base.BaseFragment
import com.example.movieapp.common.extentions.loadImage
import com.example.movieapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()

    override fun setupViews() {
        with(binding) {
            ivBack.setOnClickListener {
                viewModel.setEvent(DetailEvent.BackClicked)
            }
        }
    }

    override suspend fun collectEffectInScope() {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is DetailEffect.GoToBack -> {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override suspend fun collectStateInScope() {
        with(binding) {
            viewModel.state.collectLatest { state ->
                ivMovie.loadImage(state.detail.posterPath)
                tvMovieName.text = state.detail.name
                tvMovieOverview.text = state.detail.overview
            }
        }
    }
}


