package com.example.movieapp.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private val homeAdapter by lazy {
        HomeAdapter(
            ::onMovieClick
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        println(viewModel.homePaging)

        binding.rvMovies.adapter = homeAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            homeAdapter.loadStateFlow.collect { loadState ->
                // Burada, yükleme durumunu kontrol edebilir ve UI güncelleyebilirsiniz
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        // Veriler yüklenirken gösterilecek işlemler (örneğin, ProgressBar)
                        binding.progressBar.isVisible = true
                    }

                    is LoadState.NotLoading -> {
                        // Veriler yüklendikten sonra yapılacak işlemler
                        binding.progressBar.isVisible = false
                    }

                    is LoadState.Error -> {
                        // Hata durumunda yapılacak işlemler (örneğin, hata mesajı gösterme)
                        binding.progressBar.isVisible = false
                        val errorState = loadState.refresh as LoadState.Error
                        Toast.makeText(requireContext(), "Error: ${errorState.error.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homePaging.collectLatest { pagingData ->
                homeAdapter.submitData(pagingData)
            }
        }

    }
    private fun onMovieClick(id: Int) {
        //viewModel.setEvent(SearchEvent.ProductClicked(id))
    }
}
