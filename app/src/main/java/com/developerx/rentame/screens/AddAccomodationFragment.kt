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
import com.developerx.rentame.daos.AccomodationDao
import com.developerx.rentame.databinding.FragmentAddAccomodationBinding
import com.developerx.rentame.models.Accomodation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddAccomodationFragment : Fragment() {
    private var _binding: FragmentAddAccomodationBinding? = null
    private val binding get() = _binding!!

    private lateinit var accomodationDao: AccomodationDao
    private lateinit var accomodation: Accomodation

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
                    binding.mainCons.visibility = View.INVISIBLE
                    binding.progCons.visibility = View.VISIBLE

                    accomodationDao = AccomodationDao()
                    var uid = UUID.randomUUID().toString()


                    GlobalScope.launch(Dispatchers.IO) {
                        if(imageArrayList != null) {
                            loadImage()

                        }

                        accomodation = Accomodation(
                            uid,
                            binding.accomodationTitle.editText!!.text.toString(),
                            binding.nRooms.editText!!.text.toString(),
                            binding.nWashrooms.editText!!.text.toString(),
                            binding.price.editText!!.text.toString(),
                            binding.furnitures.editText!!.text.toString(),
                            binding.location.editText!!.text.toString(),
                            binding.details.editText!!.text.toString(),
                            firebaseImageArrayList,
                            System.currentTimeMillis()
                        )
                        Log.e("upload", "uploaded")
                        accomodationDao.uploadAccomodation(accomodation)


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
        val action = AddAccomodationFragmentDirections.actionAddAccomodationFragmentToAdminAccomodationFragment()
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
                    imageList!!.add(SlideModel(imageUri.toString(),ScaleTypes.FIT))

                }

            } else if (data?.data != null) {
                // if single image is selected
                imageArrayList = ArrayList<Uri>()
                var imageUri: Uri = data.data!!
                imageArrayList?.add(imageUri)
                imageList!!.add(SlideModel(imageUri.toString(),ScaleTypes.FIT))
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
