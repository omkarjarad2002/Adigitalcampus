package com.jaradomkar.realtimechat.model

data class Post(
    val email:String,
    val password:String,
    val cpassword:String
)

data class LoginData(
    val email:String,
    val password: String
)

data class RegisterData(
    val name:String,
    val email:String,
    val phone:Long,
    val branch:String,
    val year:Int
)
