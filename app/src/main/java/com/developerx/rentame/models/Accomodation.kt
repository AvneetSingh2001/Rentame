package com.developerx.rentame.models

data class Accomodation (
    var id: String,
    var title: String,
    var nRooms: String,
    var nWcs: String,
    var price: String,
    var fur: String,
    var location: String,
    var desc: String,
    var img1: String?,
    var img2: String?,
    var img3: String?,
    var img4: String?,
    var img5: String?,
    var createdAt: Long = 0L,
)