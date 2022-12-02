package com.developerx.rentame.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developerx.rentame.R
import com.developerx.rentame.clickListeners.JobClickListener
import com.developerx.rentame.databinding.ItemJobBinding
import com.developerx.rentame.models.Jobs
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class JobAdapter(val options: FirestoreRecyclerOptions<Jobs>, private val jobClickListener: JobClickListener): FirestoreRecyclerAdapter<Jobs, JobAdapter.jobAdapterViewHolder>(options) {

    class jobAdapterViewHolder(val binding: ItemJobBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jobAdapterViewHolder {
        return jobAdapterViewHolder(ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: jobAdapterViewHolder, position: Int, model: Jobs) {
        val context = holder.itemView.context
        holder.binding.apply {
            tvTitle.text = model.jobTitle
            tvPrice.text = "$${model.jobSalary}/hr"
            tvOrg.text = "${model.jobProvider}"
            if(model.jobImg != null) {
                Glide.with(context).load(model.jobImg!!.get(0)).error(R.drawable.envision).into(ivJob)
            }

            jobView.setOnClickListener {
                jobClickListener.itemClickListener(model)
            }
        }
    }
}