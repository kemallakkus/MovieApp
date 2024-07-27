package com.example.movieapp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.navController

            NavigationUI.setupWithNavController(bottomNavView, navController)

            val fragmentList = arrayListOf(
                R.id.homeFragment,
                R.id.favoriteFragment,
                R.id.profileFragment
            )

            navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNavView.isVisible = fragmentList.contains(destination.id)
            }

            if (intent?.action == Intent.ACTION_VIEW) {
                handleIntent(intent)
            }
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        if (intent.action == Intent.ACTION_VIEW) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent?) {
        val uri = intent?.data
        uri?.let {
            if (it.toString().startsWith("movieapp://auth")) {
                val requestToken = it.getQueryParameter("request_token")
                if (requestToken != null) {
                    val bundle = Bundle()
                    bundle.putString("request_token", requestToken)
                    navController.navigate(R.id.authFragment, bundle)
                }
            }
        }
    }
}