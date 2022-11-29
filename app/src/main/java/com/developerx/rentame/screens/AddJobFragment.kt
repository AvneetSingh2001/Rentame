package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developerx.rentame.R
import com.developerx.rentame.daos.JobDao
import com.developerx.rentame.databinding.FragmentAddJobBinding
import com.developerx.rentame.models.Jobs
import kotlinx.coroutines.Job
import java.util.*


class AddJobFragment : Fragment() {

    private var _binding: FragmentAddJobBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobDao: JobDao
    private lateinit var jobs: Jobs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddJobBinding.inflate(inflater, container, false)

        binding.apply {
            upload.setOnClickListener {
                if(jobTitle.editText!!.text.isNullOrBlank()) {
                    jobTitle.error = "Required"
                } else if(organization.editText!!.text.isNullOrBlank()) {
                    organization.error = "Required"
                } else if(salary.editText!!.text.isNullOrBlank()) {
                    salary.error = "Required"
                } else if(location.editText!!.text.isNullOrBlank()) {
                    location.error = "Required"
                } else if(details.editText!!.text.isNullOrBlank()) {
                    details.error = "Required"
                } else {
                    jobDao = JobDao()
                    var uid = UUID.randomUUID().toString()
                    jobs = Jobs(
                        uid,
                        jobTitle.editText!!.text.toString(),
                        organization.editText!!.text.toString(),
                        salary.editText!!.text.toString(),
                        location.editText!!.text.toString(),
                        details.editText!!.text.toString(),
                        null,
                        System.currentTimeMillis()
                    )

                    jobDao.uploadJob(jobs)
                }
            }
        }
        return binding.root
    }


}