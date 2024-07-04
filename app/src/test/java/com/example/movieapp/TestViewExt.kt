package com.example.movieapp

import android.content.Context
import android.view.View
import com.example.movieapp.common.extentions.visible
import org.junit.Assert.assertEquals
import org.junit.Test
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class ViewExtensionsTest {

    @Test
    fun testVisibleExtension() {
        // Bağlamı alın
        val context: Context = RuntimeEnvironment.getApplication()

        // Bir View nesnesi oluşturun
        val view = View(context)

        // Başlangıçta görünürlüğün GONE olduğunu varsayalım
        view.visibility = View.GONE

        // visible() fonksiyonunu çağırın
        view.visible()

        // Görünürlüğün VISIBLE olup olmadığını kontrol edin
        assertEquals(View.VISIBLE, view.visibility)
    }
}
