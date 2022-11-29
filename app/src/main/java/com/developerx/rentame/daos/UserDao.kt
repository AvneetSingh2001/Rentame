package com.developerx.rentame.daos

import com.developerx.rentame.models.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {
    val db = Firebase.firestore
    val ref = db.collection("users")
    val adminRef = db.collection("admin")

    fun addUser(user : Users){
        GlobalScope.launch(Dispatchers.IO) {
            ref.document(user.id).set(user)
        }
    }

    fun updateUser(user: Users){
        GlobalScope.launch(Dispatchers.IO) {
            ref.document(user.id).set(user)
        }
    }

    fun getAdminById(id : String): Task<DocumentSnapshot> {
        return adminRef.document(id).get()
    }

    fun getUserById(id : String): Task<DocumentSnapshot>{
        return ref.document(id).get()
    }
}