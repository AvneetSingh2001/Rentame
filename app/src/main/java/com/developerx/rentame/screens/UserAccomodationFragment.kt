package com.developerx.rentame.screens

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
import com.developerx.rentame.databinding.FragmentUserAccomodationBinding
import com.developerx.rentame.models.Accomodation
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class UserAccomodationFragment : Fragment(), AccomodationClickListener {
    private var _binding : FragmentUserAccomodationBinding ?= null
    private val binding get() = _binding!!

    private lateinit var accomodationDao: AccomodationDao
    private lateinit var adapter: AccomodationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserAccomodationBinding.inflate(inflater, container, false)

        binding.mainCons.visibility = View.INVISIBLE
        binding.progCons.visibility = View.VISIBLE

        setUpRecyclerView()

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
        binding.mainCons.visibility = View.VISIBLE
        binding.progCons.visibility = View.INVISIBLE
    }

    override fun itemClickListener(accomodation: Accomodation) {
        val action = UserAccomodationFragmentDirections.actionUserAccomodationFragmentToDetailedAccomodationFragment2(accomodation, 1)
        requireView().findNavController().navigate(action)
    }

}