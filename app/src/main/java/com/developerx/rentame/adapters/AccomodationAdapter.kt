package com.developerx.rentame.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.developerx.rentame.R
import com.developerx.rentame.clickListeners.AccomodationClickListener
import com.developerx.rentame.databinding.ItemAccomodationBinding
import com.developerx.rentame.models.Accomodation
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class AccomodationAdapter(options: FirestoreRecyclerOptions<Accomodation>, private val accomodationClickListener: AccomodationClickListener) : FirestoreRecyclerAdapter<Accomodation, AccomodationAdapter.AccomodationViewHolder>(options){


    class AccomodationViewHolder(val binding: ItemAccomodationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccomodationViewHolder {
        return AccomodationViewHolder(ItemAccomodationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: AccomodationViewHolder,
        position: Int,
        model: Accomodation
    ) {
        val context = holder.itemView.context

        holder.binding.apply {
            tvTitle.text = model.title
            tvPrice.text = "$${model.price}"
            tvDesc.text = "${model.nRooms} bd ${model.nWcs} ba"
            if(model.img1 != null) {
               Glide.with(context).load(model.img1!![0]).error(R.drawable.envision).into(ivAccomodation)
            }

            accomodationView.setOnClickListener {
                accomodationClickListener.itemClickListener(model)
            }

        }
    }
}