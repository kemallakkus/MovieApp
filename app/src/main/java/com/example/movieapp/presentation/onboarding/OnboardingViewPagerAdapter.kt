package com.example.movieapp.presentation.onboarding

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.R

class OnboardingViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FIRST_SCREEN -> OnboardingItemFragment.newInstance(
                context.resString(R.string.onboarding_title_1),
                context.resString(R.string.onboarding_desc_1),
                R.raw.movie
            )

            SECOND_SCREEN -> OnboardingItemFragment.newInstance(
                context.resString(R.string.onboarding_title_2),
                context.resString(R.string.onboarding_desc_2),
                R.raw.movie
            )

            else -> OnboardingItemFragment.newInstance(
                context.resString(R.string.onboarding_title_2),
                context.resString(R.string.onboarding_desc_2),
                R.raw.movie
            )
        }
    }

    private fun Context.resString(resId: Int) = ContextCompat.getString(this, resId)

    override fun getItemCount() = 3

    companion object {
        private const val FIRST_SCREEN = 0
        private const val SECOND_SCREEN = 1
    }
}