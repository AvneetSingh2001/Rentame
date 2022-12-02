package com.developerx.rentame.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Jobs(
    var id: String = "",
    var jobTitle: String = "",
    var jobProvider: String = "",
    var jobSalary: String = "",
    var jobLocation: String = "",
    var jobDesc: String = "",
    var jobImg: ArrayList<String>? = null,
    var createdAt: Long = 0L
) : Parcelable