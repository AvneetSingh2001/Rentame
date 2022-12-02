package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.developerx.rentame.R
import com.developerx.rentame.databinding.FragmentUserSubscriptionBinding


class UserSubscriptionFragment : Fragment() {

    private var _binding: FragmentUserSubscriptionBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserSubscriptionBinding.inflate(inflater, container, false)

        binding.apply {
            basicCard.setOnClickListener {
                Toast.makeText(activity, "Will be implemented soon...", Toast.LENGTH_SHORT).show()
            }

            premCard.setOnClickListener {
                Toast.makeText(activity, "Will be implemented soon...", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root

    }
}