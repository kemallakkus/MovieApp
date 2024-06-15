package com.example.movieapp.common.extentions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


/**
 * Özellikle veri akışlarını UI güncellemeleriyle eşlemek için kullanılır.
 * LifecycleOwner ile olan versiyon, yaşam döngüsü yönetimini otomatik olarak sağlar ve gerektiği durumda veri akışını durdurur.
 * CoroutineScope ile olan versiyon ise daha genel amaçlıdır ve belirtilen scope'un yaşam döngüsü boyunca çalışır.
 */

fun <T> Flow<T>.collect(lifecycleOwner: LifecycleOwner, function: (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) { // onResume çağrıldığında veri akışını dinlemeye başlar
            collect { //her yeni veri geldiğinde fonksiyonu çağır
                function(it)
            }
        }
    }
}

fun <T> Flow<T>.collect(coroutineScope: CoroutineScope, function: (T) -> Unit) {
    coroutineScope.launch {
        collect {
            function(it)
        }
    }
}