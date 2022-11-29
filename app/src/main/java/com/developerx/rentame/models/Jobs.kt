package com.developerx.rentame.models

data class Jobs(
    var id: String,
    var jobTitle: String,
    var jobProvider: String,
    var jobSalary: String,
    var jobLocation: String,
    var jobDesc: String,
    var jobImg: String?,
    var createdAt: Long = 0L
)