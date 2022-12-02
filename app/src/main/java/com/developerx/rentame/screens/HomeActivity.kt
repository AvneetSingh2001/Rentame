package com.developerx.rentame.screens

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.developerx.rentame.MainActivity
import com.developerx.rentame.R
import com.developerx.rentame.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private var dynamicLink: String? = null
    private var auth: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)


        auth = Firebase.auth.currentUser

        val navController  = findNavController(R.id.fragmentContainerView)
        navController.setGraph(
            R.navigation.nav_graph_inner, HomeFragmentArgs(
                dynamicLink
            ).toBundle())
        binding!!.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.userAccomodationFragment -> showBottomNav()
                R.id.userJobFragment -> showBottomNav()
                R.id.dealsFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
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