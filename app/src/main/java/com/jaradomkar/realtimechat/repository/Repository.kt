package com.jaradomkar.realtimechat.repository

import com.jaradomkar.realtimechat.Api.RetrofitInstance
import com.jaradomkar.realtimechat.model.LoginData
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.model.RegisterData
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post>{
        return RetrofitInstance.api.getPost()
    }
    suspend fun pushLoginData(data: LoginData): Response<LoginData>{
        return RetrofitInstance.api.pushLoginData(data)
    }

    suspend fun pushPost(post: Post): Response<Post>{
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushRegisterData(data: RegisterData): Response<RegisterData>{
        return RetrofitInstance.api.pushRegisterData(data)
    }

}