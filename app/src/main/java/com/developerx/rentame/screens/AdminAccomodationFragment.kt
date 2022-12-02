package com.developerx.rentame.screens

import android.app.DownloadManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.developerx.rentame.R
import com.developerx.rentame.adapters.AccomodationAdapter
import com.developerx.rentame.clickListeners.AccomodationClickListener
import com.developerx.rentame.daos.AccomodationDao
import com.developerx.rentame.databinding.FragmentAdminAccomodationBinding
import com.developerx.rentame.models.Accomodation
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class AdminAccomodationFragment : Fragment(), AccomodationClickListener {
    private var _binding : FragmentAdminAccomodationBinding? = null
    private val binding get() = _binding!!

    private lateinit var accomodationDao: AccomodationDao
    private lateinit var adapter: AccomodationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdminAccomodationBinding.inflate(inflater, container, false)


        setUpRecyclerView()

        binding.addAccomodationBtn.setOnClickListener {
            val action = AdminAccomodationFragmentDirections.actionAdminAccomodationFragmentToAddAccomodationFragment()
            requireView().findNavController().navigate(action)
        }


        return binding.root
    }

    private fun setUpRecyclerView(){
        accomodationDao = AccomodationDao()
        val accomodationCollection = accomodationDao.accomdationRef
        val query = accomodationCollection.orderBy("createdAt", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Accomodation>().setQuery(query, Accomodation::class.java).build()
        adapter = AccomodationAdapter(options, this)
        binding.llAccomodationRv.adapter = adapter
        binding.llAccomodationRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.startListening()
    }

    override fun itemClickListener(accomodation: Accomodation) {
        val action = AdminAccomodationFragmentDirections.actionAdminAccomodationFragmentToDetailedAccomodationFragment(accomodation, 0)
        requireView().findNavController().navigate(action)
    }

}