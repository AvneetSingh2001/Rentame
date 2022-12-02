package com.developerx.rentame.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Accomodation (
    var id: String = "",
    var title: String = "",
    var nRooms: String = "",
    var nWcs: String = "",
    var price: String = "",
    var fur: String = "",
    var location: String = "",
    var desc: String = "",
    var img1: ArrayList<String>? = null,
    var createdAt: Long = 0L,
) : Parcelable