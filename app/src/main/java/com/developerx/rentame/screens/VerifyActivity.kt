package com.developerx.rentame.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.developerx.rentame.R
import com.developerx.rentame.databinding.ActivityAdminBinding
import com.developerx.rentame.databinding.ActivityVerifyBinding

class VerifyActivity : AppCompatActivity() {

    private var _binding: ActivityVerifyBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVerifyBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)


        val navController  = findNavController(R.id.verifyFragmentContainerView)
        navController.setGraph(
            R.navigation.nav_graph)

    }
}