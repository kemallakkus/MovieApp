package com.example.movieapp.common.extentions

import android.util.Log
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
    progressBar: View,
    recyclerView: RecyclerView,
    onError: (LoadState.Error) -> Unit = { error ->
        // Varsayılan hata işleyici
        Log.e("LoadStateError", "Error: ${error.error.message}")
    },
) {
    // Yenileme durumlarını kontrol et
    setLoadState(refresh, progressBar, recyclerView, onError)

    // Ekleme durumlarını kontrol et
    setLoadState(append, progressBar, recyclerView, onError)
}

private fun setLoadState(
    loadState: LoadState,
    progressBar: View,
    recyclerView: RecyclerView,
    onError: (LoadState.Error) -> Unit,
) {
    when (loadState) {
        is LoadState.Loading -> showLoading(progressBar, recyclerView)
        is LoadState.NotLoading -> showContent(progressBar, recyclerView)
        is LoadState.Error -> showError(loadState, onError)
        else -> Unit // Diğer durumlar için
    }
}

private fun showLoading(progressBar: View, recyclerView: RecyclerView) {
    progressBar.visible() // İlerleme çubuğunu göster
    recyclerView.gone() // RecyclerView'ı gizle
}

private fun showContent(progressBar: View, recyclerView: RecyclerView) {
    progressBar.gone() // İlerleme çubuğunu gizle
    recyclerView.visible() // RecyclerView'ı göster
}

private fun showError(errorState: LoadState.Error, onError: (LoadState.Error) -> Unit) {
    onError.invoke(errorState) // Hata mesajını onError fonksiyonuna ilet
}

