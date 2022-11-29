package com.developerx.rentame.daos

import com.developerx.rentame.models.Accomodation
import com.developerx.rentame.models.Jobs
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class JobDao {
    val db = Firebase.firestore
    val jobRef = db.collection("jobs")


    fun uploadJob(job: Jobs) {
        GlobalScope.launch(Dispatchers.IO) {
            jobRef.document().set(job)
        }
    }

    fun deleteJob(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            jobRef.document(id).delete()
        }
    }

    fun getJobById(id: String): Task<DocumentSnapshot> {
        return jobRef.document().get()
    }
}