package com.example.movieapp.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

/**
 * BaseFragment sınıfı, ViewBinding ve veri toplama işlemlerini
 * yönetmek için bir temel fragment sınıfıdır. Bu sınıf, ViewBinding'in kullanımını
 * kolaylaştırır ve veri akışlarını (state ve effect) yaşam döngüsüne duyarlı olarak toplar.
 *
 * @param VB : ViewBinding tipini belirtir.
 * @param inflate : ViewBinding nesnesini oluşturmak için kullanılan bir lambda fonksiyonudur.
 *
 * Özellikler:
 * - _binding: ViewBinding nesnesini saklayan özel bir değişkendir.
 * - binding: _binding'e güvenli erişim sağlar.
 */
abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectState()
        collectEffect()
    }

    private fun collectEffect() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectEffectInScope()
            }
        }
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectStateInScope()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun setupViews()
    abstract suspend fun collectEffectInScope()
    abstract suspend fun collectStateInScope()
}