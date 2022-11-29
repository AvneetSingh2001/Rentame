package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.developerx.rentame.R
import com.developerx.rentame.databinding.FragmentAdminAccomodationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AdminAccomodationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminAccomodationFragment : Fragment() {
    private var _binding : FragmentAdminAccomodationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminAccomodationBinding.inflate(inflater, container, false)

        binding.addAccomodationBtn.setOnClickListener {
            val action = AdminAccomodationFragmentDirections.actionAdminAccomodationFragmentToAddAccomodationFragment()
            requireView().findNavController().navigate(action)
        }


        return binding.root
    }

}