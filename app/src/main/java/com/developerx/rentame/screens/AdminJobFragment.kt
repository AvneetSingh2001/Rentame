package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.developerx.rentame.R
import com.developerx.rentame.databinding.FragmentAdminJobBinding


class AdminJobFragment : Fragment() {
    private var _binding : FragmentAdminJobBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminJobBinding.inflate(inflater, container, false)

        binding.addJobBtn.setOnClickListener {
            val action = AdminJobFragmentDirections.actionAdminJobFragmentToAddJobFragment()
            requireView().findNavController().navigate(action)
        }

        return binding.root
    }

}