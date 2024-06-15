package com.example.movieapp.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.R
import com.example.movieapp.common.viewBinding
import com.example.movieapp.databinding.FragmentOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private val binding by viewBinding(FragmentOnboardingBinding::bind)
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.isFirstTime.collect { isFirstTime ->
                isFirstTime?.let {
                    if (!it) {
                        findNavController().navigate(R.id.onboardingFragmentToHomeFragment)
                    } else {
                        setupOnboarding()
                    }
                }
            }
        }
    }

    private fun setupOnboarding() {
        with(binding) {
            viewPager.adapter = OnboardingViewPagerAdapter(requireActivity(), requireContext())
            viewPager.offscreenPageLimit = 1

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    btnNext.text = if (position == 2) {
                        getString(R.string.finish)
                    } else {
                        getString(R.string.next)
                    }
                }

                override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) = Unit
                override fun onPageScrollStateChanged(arg0: Int) = Unit
            })

            TabLayoutMediator(pageIndicator, viewPager) { _, _ -> }.attach()

            btnNext.setOnClickListener {
                if (getItem() > viewPager.childCount) {
                    findNavController().navigate(R.id.onboardingFragmentToHomeFragment)
                } else {
                    viewPager.setCurrentItem(getItem() + 1, true)
                }
            }

            btnBack.setOnClickListener {
                if (getItem() == 0) {
                    requireActivity().finish()
                } else {
                    viewPager.setCurrentItem(getItem() - 1, true)
                }
            }

            tvSkip.setOnClickListener {
                viewModel.onEvent(OnboardingEvent.SaveAppEntry)
                findNavController().navigate(R.id.onboardingFragmentToHomeFragment)
            }
        }
    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }
}
