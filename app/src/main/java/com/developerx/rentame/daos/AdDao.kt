package com.developerx.rentame.daos

import com.developerx.rentame.models.Ad
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdDao {

    var db = FirebaseFirestore.getInstance()
    val adRef = db.collection("posts")
    val auth = Firebase.auth

    fun addPost(text: String, imageUrl : String?){
        val currentUserId = auth.currentUser!!.uid!!
        GlobalScope.launch {

            val currentTime = System.currentTimeMillis()
           // val posts = Ad(text, currentUserId, currentTime, imageUrl)
           // adRef.document().set(posts)


        }
    }

    fun getPostById(postId : String): Task<DocumentSnapshot> {
        return adRef.document(postId).get()
    }

    fun updateLikes(postId: String){
        GlobalScope.launch{
            val currentUserId = auth.currentUser!!.uid
            getPostById(postId).addOnSuccessListener {
                val post = it.toObject(Ad::class.java)!!
//                val isLiked = post.likedBy.contains(currentUserId)
//                if(isLiked){
//                    post.likedBy.remove(currentUserId)
//                }else
//                    post.likedBy.add(currentUserId)

                adRef.document(postId).set(post)
            }
        }
    }

    fun deletePost(postId: String): Task<Void> {
        return adRef.document(postId).delete()
    }
}