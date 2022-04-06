package com.jaradomkar.realtimechat.model

data class Post(
    val email:String,
    val password:String,
    val cpassword:String
)

data class EmailVerificationData(
    val email:String
)
data class EmailVerificationOtpResponse(
    val email:String,
    val final__otp:String
)

data class LoginData(
    val email:String,
    val password:String
)

data class ChangePasswordData(
    val otp:String,
    val otp_code:String,
    val email:String,
    val password: String,
    val cpassword: String
)

data class ChangedPasswordResponse(
    val responce:String
)

data class LoginResponce(
    val user:User
)

data class User(
    val _id:String,
    val email: String,
    val isadmin:Boolean,
    val isteacher:Boolean
)

data class RegisterData(
    val name:String,
    val email:String,
    val phone:Long,
    val branch:String,
    val year:Int
)
