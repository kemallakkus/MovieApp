package com.example.movieapp.common.extentions

import android.view.View
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

fun CombinedLoadStates.handleLoadStates(
    progressBar: View,
    recyclerView: RecyclerView,
    onError: (LoadState.Error) -> Unit = { },
) {
    when (val refreshState = refresh) {
        is LoadState.Loading -> showLoading(progressBar, recyclerView)
        is LoadState.NotLoading -> showContent(progressBar, recyclerView)
        is LoadState.Error -> showError(refreshState, onError)
        else -> Unit // Handle other states if needed
    }

    when (val appendState = append) {
        is LoadState.Loading -> showLoading(progressBar, recyclerView)
        is LoadState.NotLoading -> showContent(progressBar, recyclerView)
        is LoadState.Error -> showError(appendState, onError)
        else -> Unit // Handle other states if needed
    }
}

private fun showLoading(progressBar: View, recyclerView: RecyclerView) {
    progressBar.visible()
    recyclerView.gone()
}

private fun showContent(progressBar: View, recyclerView: RecyclerView) {
    progressBar.gone()
    recyclerView.visible()
}

private fun showError(errorMessage: LoadState.Error?, onError: (LoadState.Error) -> Unit) {
    errorMessage?.let {
        onError.invoke(errorMessage)
    }
}
