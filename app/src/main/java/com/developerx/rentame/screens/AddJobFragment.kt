package com.developerx.rentame.screens

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.developerx.rentame.R
import com.developerx.rentame.daos.JobDao
import com.developerx.rentame.databinding.FragmentAddJobBinding
import com.developerx.rentame.models.Jobs
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.ArrayList


class AddJobFragment : Fragment() {

    private var _binding: FragmentAddJobBinding? = null
    private val binding get() = _binding!!

    private lateinit var jobDao: JobDao
    private lateinit var jobs: Jobs

    private val REQUEST_CODE = 200
    private var imageArrayList: ArrayList<Uri>? = null
    private var firebaseImageArrayList: ArrayList<String>? = null

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

                    binding.mainCons.visibility = View.INVISIBLE
                    binding.progCons.visibility = View.VISIBLE

                    GlobalScope.launch(Dispatchers.IO) {
                        if (imageArrayList != null) {
                            loadImage()

                        }


                        jobs = Jobs(
                            uid,
                            jobTitle.editText!!.text.toString(),
                            organization.editText!!.text.toString(),
                            salary.editText!!.text.toString(),
                            location.editText!!.text.toString(),
                            details.editText!!.text.toString(),
                            firebaseImageArrayList,
                            System.currentTimeMillis()
                        )

                        jobDao.uploadJob(jobs)

                        withContext(Dispatchers.Main) {
                            binding.mainCons.visibility = View.VISIBLE
                            binding.progCons.visibility = View.INVISIBLE
                            navigateBack()
                        }
                    }
                }
            }

            btnImageUpload.setOnClickListener {
                openGalleryForImages()
            }

        }
        return binding.root
    }

    private fun navigateBack() {
        val action = AddJobFragmentDirections.actionAddJobFragmentToAdminJobFragment()
        requireView().findNavController().navigate(action)
    }
    private fun openGalleryForImages() {
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            var imageList = ArrayList<SlideModel>()
            // if multiple images are selected
            if (data?.clipData != null) {
                var count = data.clipData?.itemCount
                imageArrayList = ArrayList<Uri>()


                for (i in 0 until count!!) {
                    Log.e("image $i", "added in imageList")
                    var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
                    imageArrayList?.add(imageUri)
                    imageList!!.add(SlideModel(imageUri.toString(), ScaleTypes.FIT))

                }

            } else if (data?.data != null) {
                // if single image is selected
                imageArrayList = ArrayList<Uri>()
                var imageUri: Uri = data.data!!
                imageArrayList?.add(imageUri)
                imageList!!.add(SlideModel(imageUri.toString(), ScaleTypes.FIT))
            }


            binding.imageSlider.setImageList(imageList)
            binding.imageSlider.visibility = View.VISIBLE


        }
    }

    suspend fun loadImage() {
        firebaseImageArrayList = ArrayList<String>()

        Log.e("IN FUN LOAD IMG", "added in firebase imageList")

        for(i in imageArrayList!!) {
            var id = UUID.randomUUID().toString()
            var uriTask = FirebaseStorage.getInstance().getReference("images/$id").putFile(i).await()
            var uri = uriTask.storage.downloadUrl.await()
            Log.e("uri", uri.toString())
            firebaseImageArrayList?.add(uri.toString())
            Log.e("image $i", "added in firebase imageList")
        }
    }
}