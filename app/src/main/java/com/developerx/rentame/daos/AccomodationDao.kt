package com.developerx.rentame.daos

import com.developerx.rentame.models.Accomodation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AccomodationDao {
    val db = Firebase.firestore
    val accomdationRef = db.collection("accomodations")


    fun uploadAccomodation(accomodation: Accomodation) {
        GlobalScope.launch(Dispatchers.IO) {
            accomdationRef.document().set(accomodation)
        }
    }

    fun deleteAccomodation(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            accomdationRef.document(id).delete()
        }
    }

    fun getAccomodationById(id: String): Task<DocumentSnapshot> {
        return accomdationRef.document().get()
    }
}