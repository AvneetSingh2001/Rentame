package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developerx.rentame.R
import com.developerx.rentame.daos.AccomodationDao
import com.developerx.rentame.databinding.FragmentAddAccomodationBinding
import com.developerx.rentame.models.Accomodation
import java.util.*

class AddAccomodationFragment : Fragment() {
    private var _binding: FragmentAddAccomodationBinding? = null
    private val binding get() = _binding!!

    private lateinit var accomodationDao: AccomodationDao
    private lateinit var accomodation: Accomodation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddAccomodationBinding.inflate(inflater, container, false)
        binding.apply {
            upload.setOnClickListener {
                if(binding.accomodationTitle.editText!!.text.isNullOrBlank()) {
                    binding.accomodationTitle.error = "Required"
                } else if(binding.nRooms.editText!!.text.isNullOrBlank()) {
                    binding.nRooms.error = "Required"
                } else if(binding.nWashrooms.editText!!.text.isNullOrBlank()) {
                    binding.nWashrooms.error = "Required"
                } else if(binding.price.editText!!.text.isNullOrBlank()) {
                    binding.price.error = "Required"
                } else if(binding.furnitures.editText!!.text.isNullOrBlank()) {
                    binding.furnitures.error = "Required"
                } else if(binding.details.editText!!.text.isNullOrBlank()) {
                    binding.details.error = "Required"
                } else {
                    accomodationDao = AccomodationDao()
                    var uid = UUID.randomUUID().toString()
                    accomodation = Accomodation(
                        uid,
                        binding.accomodationTitle.editText!!.text.toString(),
                        binding.nRooms.editText!!.text.toString(),
                        binding.nWashrooms.editText!!.text.toString(),
                        binding.price.editText!!.text.toString(),
                        binding.furnitures.editText!!.text.toString(),
                        binding.location.editText!!.text.toString(),
                        binding.details.editText!!.text.toString(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        System.currentTimeMillis()
                    )
                    accomodationDao.uploadAccomodation(accomodation)
                }
            }
        }

        return binding.root
    }

}