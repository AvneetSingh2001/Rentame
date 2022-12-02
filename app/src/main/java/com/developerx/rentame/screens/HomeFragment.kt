package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.developerx.rentame.R
import com.developerx.rentame.daos.UserDao
import com.developerx.rentame.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        userDao = UserDao()
        binding.apply {
            Glide.with(requireContext()).load(FirebaseAuth.getInstance().currentUser!!.photoUrl).circleCrop().error(R.drawable.ic_baseline_account_circle_24).into(profileImage)
            nameTv.text = FirebaseAuth.getInstance().currentUser!!.displayName.toString()
            btnBusiness.setOnClickListener {
                Toast.makeText(activity, "Will be implemented soon...", Toast.LENGTH_SHORT).show()
            }
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