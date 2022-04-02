package com.jaradomkar.realtimechat.Api

import com.jaradomkar.realtimechat.model.LoginData
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.model.RegisterData
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

    @POST("/login")
    suspend fun pushLoginData(
        @Body post: LoginData
    ): Response<LoginData>

    @POST("/register")
    suspend fun pushRegisterData(
        @Body post: RegisterData
    ): Response<RegisterData>

}