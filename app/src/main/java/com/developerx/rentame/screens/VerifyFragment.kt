package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.findNavController
import com.developerx.rentame.R
import com.developerx.rentame.databinding.FragmentVerifyBinding
import com.developerx.rentame.keys


class VerifyFragment : Fragment() {

    private var _binding : FragmentVerifyBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVerifyBinding.inflate(inflater, container, false)


        binding.passBtn.setOnClickListener {
            var key:String = binding.etBio.editText!!.text.toString()
            if(key.equals("envision123")) {
                moveToAdminScreen()
            }else {
                binding.etBio.error = "Incorrect Key"
            }
        }

        return binding.root
    }

    private fun moveToAdminScreen() {
        binding.etBio.editText!!.text.clear()
        val action = VerifyFragmentDirections.actionVerifyFragmentToAdminActivity()
        requireView().findNavController().navigate(action)
    }

}