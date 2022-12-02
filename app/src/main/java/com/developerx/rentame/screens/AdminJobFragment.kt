package com.developerx.rentame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerx.rentame.R
import com.developerx.rentame.adapters.JobAdapter
import com.developerx.rentame.clickListeners.JobClickListener
import com.developerx.rentame.daos.JobDao
import com.developerx.rentame.databinding.FragmentAdminJobBinding
import com.developerx.rentame.models.Jobs
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class AdminJobFragment : Fragment(), JobClickListener {
    private var _binding : FragmentAdminJobBinding ?= null
    private val binding get() = _binding!!

    private lateinit var jobDao: JobDao
    private lateinit var adapter: JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminJobBinding.inflate(inflater, container, false)

        setUpRecyclerView()

        binding.addJobBtn.setOnClickListener {
            val action = AdminJobFragmentDirections.actionAdminJobFragmentToAddJobFragment()
            requireView().findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setUpRecyclerView() {
        jobDao = JobDao()
        val jobCollection = jobDao.jobRef
        val query = jobCollection.orderBy("createdAt", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Jobs>().setQuery(query, Jobs::class.java).build()
        adapter = JobAdapter(options, this)
        binding.allJobRv.adapter = adapter
        binding.allJobRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.startListening()
    }

    override fun itemClickListener(jobs: Jobs) {
        val action = AdminJobFragmentDirections.actionAdminJobFragmentToDetailedJobFragment(jobs, 0)
        requireView().findNavController().navigate(action)
    }

}