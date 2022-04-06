package com.jaradomkar.realtimechat.Api

import com.jaradomkar.realtimechat.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SimpleApi {

    @GET("posts")
    suspend fun getPost(): Response<Post>

    @POST("signup")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    @POST("emailSendForOtp")
    suspend fun pushEmailVerification(
        @Body post: EmailVerificationData
    ): Response<EmailVerificationOtpResponse>

    @POST("emailSendForOtp")
    suspend fun pushChangedPassword(
        @Body post: ChangePasswordData
    ): Response<ChangedPasswordResponse>

    @POST("login")
    suspend fun pushLoginData(
        @Body post: LoginData
    ): Response<LoginResponce>

    @POST("register")
    suspend fun pushRegisterData(
        @Body post: RegisterData
    ): Response<RegisterData>

}