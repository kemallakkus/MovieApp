package com.example.movieapp.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RawRes
import com.example.movieapp.R
import com.example.movieapp.common.viewBinding
import com.example.movieapp.databinding.FragmentOnboardingItemBinding

class OnboardingItemFragment : Fragment(R.layout.fragment_onboarding_item) {

    private val binding by viewBinding(FragmentOnboardingItemBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            arguments?.let {
                tvTitle.text = it.getString(ARG_TITLE)
                tvDesc.text = it.getString(ARG_DESC)
                lavOnboarding.setAnimation(it.getInt(ARG_LOTTIE_RES))
            }
        }
    }

    companion object {

        private const val ARG_TITLE = "param1"
        private const val ARG_DESC = "param2"
        private const val ARG_LOTTIE_RES = "param3"

        @JvmStatic
        fun newInstance(title: String, desc: String, @RawRes lottieRes: Int) =
            OnboardingItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESC, desc)
                    putInt(ARG_LOTTIE_RES, lottieRes)
                }
            }
    }
}