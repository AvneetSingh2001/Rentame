package com.developerx.rentame.models



data class Users(
    var id : String = "",
    var name : String = "",
    var imageUrl: String = "",
    var rank : Int = 0,               // 0 -> not subscribed, 1 --> subscriber, 2 --> admin
    var bio: String = "Hey! I am here !!"
)