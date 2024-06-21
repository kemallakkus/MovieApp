package com.example.movieapp.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import com.example.movieapp.common.extentions.loadImage
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.presentation.home.HomeEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel by viewModels<DetailViewModel>()
    private var _binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    private val binding: FragmentDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.getDetail(args.id)

        with(binding) {

            ivBack.setOnClickListener {
                viewModel.onEvent(DetailEvent.BackClicked)
            }


            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.effect.collect { effect ->
                        when (effect) {
                            DetailEffect.GoToBack -> {
                                findNavController().navigateUp()
                            }
                        }
                    }
                }
            }

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.detailState.collectLatest { data ->
                        Log.d("asdfasdasd", "Data: $data")
                        ivMovie.loadImage(data.detail.posterPath)
                        tvMovieName.text = data.detail.name
                        tvMovieOverview.text = data.detail.overview
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}