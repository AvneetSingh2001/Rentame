package com.developerx.rentame.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.developerx.rentame.R
import com.developerx.rentame.databinding.ActivityAdminBinding
import com.developerx.rentame.databinding.ActivityHomeBinding

class AdminActivity : AppCompatActivity() {
    private var _binding: ActivityAdminBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAdminBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        val navController  = findNavController(R.id.adminFragmentContainerView)
        navController.setGraph(
            R.navigation.nav_graph_admin)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.adminAccomodationFragment -> showBottomNav()
                R.id.adminJobFragment -> showBottomNav()
                R.id.dealFragment -> showBottomNav()
                R.id.adminHome -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }
}