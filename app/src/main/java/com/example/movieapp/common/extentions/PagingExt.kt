package com.example.movieapp.common.extentions

import android.view.View
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView

/**
 * Bu kod parçası, RecyclerView ve ProgressBar'ın durumunu yönetmek için CombinedLoadStates uzantısını kullanır.
 * CombinedLoadStates, veri yükleme, yenileme ve ekleme işlemleri sırasında çeşitli yükleme durumlarını yönetir.
 * Bu uzantı fonksiyonu, yükleme, başarı ve hata durumlarını uygun şekilde ele alır.
 * Hata durumunda, onError fonksiyonu çağrılarak hata mesajı işlenir.
 *
 * - `handleLoadStates` fonksiyonu, CombinedLoadStates durumlarına göre uygun işlemleri gerçekleştirir.
 * - `setLoadingState` fonksiyonu, yükleme ve başarı durumlarında ProgressBar ve RecyclerView'ın görünürlüğünü ayarlar.
 * - `showError` fonksiyonu, hata durumunda onError fonksiyonunu çağırarak hata mesajını işler.
 */
fun CombinedLoadStates.handleLoadStates(
    shimmerEffect: View,
    recyclerView: RecyclerView,
    onError: (LoadState.Error) -> Unit,
) {
    setLoadState(refresh, shimmerEffect, recyclerView, onError)

    setLoadState(append, shimmerEffect, recyclerView, onError)
}

private fun setLoadState(
    loadState: LoadState,
    shimmerEffect: View,
    recyclerView: RecyclerView,
    onError: (LoadState.Error) -> Unit,
) {
    when (loadState) {
        is LoadState.Loading -> showLoading(shimmerEffect, recyclerView)
        is LoadState.NotLoading -> showContent(shimmerEffect, recyclerView)
        is LoadState.Error -> showError(loadState, onError)
        else -> Unit
    }
}

private fun showLoading(shimmerEffect: View, recyclerView: RecyclerView) {
    shimmerEffect.visible()
    recyclerView.gone()
}

private fun showContent(shimmerEffect: View, recyclerView: RecyclerView) {
    shimmerEffect.gone()
    recyclerView.visible()
}

private fun showError(errorState: LoadState.Error, onError: (LoadState.Error) -> Unit) {
    onError.invoke(errorState)
}

