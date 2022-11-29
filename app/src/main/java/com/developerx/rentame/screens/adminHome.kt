package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.developerx.rentame.R
import com.developerx.rentame.databinding.FragmentAdminHomeBinding
import com.developerx.rentame.databinding.FragmentVerifyBinding


class adminHome : Fragment() {

    private var _binding : FragmentAdminHomeBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminHomeBinding.inflate(inflater, container, false)



        return binding.root
    }

}