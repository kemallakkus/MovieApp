package com.example.movieapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.movieapp.R
import com.example.movieapp.common.extentions.gone
import com.example.movieapp.common.extentions.visible
import com.example.movieapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val homeAdapter by lazy {
        HomeAdapter(::onMovieClick)
    }

    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            binding.rvMovies.adapter = homeAdapter

            viewLifecycleOwner.lifecycleScope.launch {
                homeAdapter.loadStateFlow.collect { loadState ->
                    // Burada, yükleme durumunu kontrol edebilir ve UI güncelleyebilirsiniz
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

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.homeState.collectLatest { pagingData ->
                    homeAdapter.submitData(pagingData.movies)
                }
            }
        }
    }

    private fun onMovieClick(id: Int) {
        //viewModel.setEvent(SearchEvent.ProductClicked(id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
