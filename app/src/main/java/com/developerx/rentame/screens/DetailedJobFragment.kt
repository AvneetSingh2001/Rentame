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
import com.developerx.rentame.databinding.FragmentDetailedJobBinding
import com.developerx.rentame.models.Jobs


class DetailedJobFragment : Fragment() {

    private var _binding : FragmentDetailedJobBinding ?= null
    private val binding get() = _binding!!

    private lateinit var jobs: Jobs
    val args : DetailedJobFragmentArgs by navArgs()
    private lateinit var mode: Integer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailedJobBinding.inflate(inflater, container, false)

        jobs = args.jobs
        val mode = args.mode

        binding.mainCons.visibility = View.INVISIBLE
        binding.progCons.visibility = View.VISIBLE
        
        val imageList = ArrayList<SlideModel>()

        binding.apply {
            tvTitle.text = jobs.jobTitle
            tvDescAns.text = jobs.jobDesc
            tvLocationAns.text = jobs.jobLocation
            tvSalaryAns.text= "$${jobs.jobSalary}/hr"
            tvOrgName.text = jobs.jobProvider

            if(jobs.jobImg != null)  {
                for(i in jobs.jobImg!!)
                    imageList.add(SlideModel(i, ScaleTypes.FIT))
            } else {
                imageList.add(SlideModel(R.drawable.envision, ScaleTypes.FIT))
            }

            if(mode == 0) {
                applyBtn.visibility = View.GONE
            }

            imageSlider.setImageList(imageList)
            binding.mainCons.visibility = View.VISIBLE
            binding.progCons.visibility = View.INVISIBLE

            applyBtn.setOnClickListener {
                val action = DetailedJobFragmentDirections.actionDetailedJobFragment2ToUserSubscriptionFragment2()
                requireView().findNavController().navigate(action)
            }

        }

        return binding.root
    }


}