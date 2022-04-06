package com.jaradomkar.realtimechat.repository

import com.jaradomkar.realtimechat.Api.RetrofitInstance
import com.jaradomkar.realtimechat.model.*
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post>{
        return RetrofitInstance.api.getPost()
    }

    suspend fun pushLoginData(post: LoginData): Response<LoginResponce>{
        return RetrofitInstance.api.pushLoginData(post)
    }

    suspend fun pushEmailVerification(post: EmailVerificationData): Response<EmailVerificationOtpResponse>{
        return RetrofitInstance.api.pushEmailVerification(post)
    }

    suspend fun pushPost(post: Post): Response<Post>{
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushRegisterData(post: RegisterData): Response<RegisterData>{
        return RetrofitInstance.api.pushRegisterData(post)
    }


    suspend fun pushChangedPassword(post: ChangePasswordData): Response<ChangedPasswordResponse>{
        return RetrofitInstance.api.pushChangedPassword(post)
    }

}