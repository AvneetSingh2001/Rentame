package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.developerx.rentame.R
import com.developerx.rentame.databinding.FragmentDetailedAccomodationBinding
import com.developerx.rentame.models.Accomodation

class DetailedAccomodationFragment : Fragment() {

    private var _binding : FragmentDetailedAccomodationBinding ?= null
    private val binding get() = _binding!!
    val args: DetailedAccomodationFragmentArgs by navArgs<DetailedAccomodationFragmentArgs>()
    private lateinit var accomodation: Accomodation
    private lateinit var mode: Integer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailedAccomodationBinding.inflate(inflater, container, false)

        binding.mainCons.visibility = View.INVISIBLE
        binding.progCons.visibility = View.VISIBLE

        accomodation = args.accomodation
        val mode = args.mode
        val imageList = ArrayList<SlideModel>()


        binding.apply {
            tvTitle.text = accomodation.title
            tvCountBed.text = accomodation.nRooms
            tvPrice.text = "$${accomodation.price}/month"
            tvDescAns.text = accomodation.desc
            tvFacilitiesAns.text = accomodation.fur
            tvLocationAns.text = accomodation.location
            tvCountWcs.text = accomodation.nWcs

            if(accomodation.img1 != null)  {
                for(i in accomodation.img1!!)
                    imageList.add(SlideModel(i, ScaleTypes.FIT))
            } else {
                imageList.add(SlideModel(R.drawable.envision, ScaleTypes.FIT))
            }

            if(mode == 0) {
                dealBtn.visibility = View.GONE
            }

            imageSlider.setImageList(imageList)
            binding.mainCons.visibility = View.VISIBLE
            binding.progCons.visibility = View.INVISIBLE

            dealBtn.setOnClickListener {
                val action = DetailedAccomodationFragmentDirections.actionDetailedAccomodationFragment2ToUserSubscriptionFragment2()
                requireView().findNavController().navigate(action)
            }
        }

        return binding.root
    }

}