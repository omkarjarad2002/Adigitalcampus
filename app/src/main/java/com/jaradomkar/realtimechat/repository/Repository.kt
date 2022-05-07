package com.jaradomkar.realtimechat.repository

import com.jaradomkar.realtimechat.Api.RetrofitInstance
import com.jaradomkar.realtimechat.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

class Repository {


    suspend fun pushEmailForUserInfo(post: userInfoData): Response<RegisterResponse>{
        return RetrofitInstance.api.pushEmailForUserInfo(post)
    }

    suspend fun pushEmailForTeacherInfo(post: teacherInfoData): Response<teacherResponse>{
        return RetrofitInstance.api.pushEmailForTeacherInfo(post)
    }

    suspend fun pushLoginData(post: LoginData): Response<LoginResponce>{
        return RetrofitInstance.api.pushLoginData(post)
    }

    suspend fun pushEmailVerification(post: EmailVerificationData): Response<EmailVerificationOtpResponse>{
        return RetrofitInstance.api.pushEmailVerification(post)
    }

    suspend fun pushEmailVerificationForSignUp(post: EmailVerificationDataForSignUp): Response<EmailVerificationSignUpOtpResponse>{
        return RetrofitInstance.api.pushEmailVerificationForSignUp(post)
    }

    suspend fun pushPost(post: Post): Response<signUpResponse>{
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushRegisterData(post: RegisterData): Response<RegisterData>{
        return RetrofitInstance.api.pushRegisterData(post)
    }

    suspend fun pushChangedPassword(post: ChangePasswordData): Response<ChangedPasswordResponse>{
        return RetrofitInstance.api.pushChangedPassword(post)
    }

    suspend fun pushTeacherData(post: RegisterTeacher): Response<RegisterTeacherResponse>{
        return RetrofitInstance.api.pushTeacherData(post)
    }

    suspend fun getRefresh(token:String): Response<tokenResponse>{
        return RetrofitInstance.api.getRefresh(token)
    }
    suspend fun getAllTeachers(): Response<ItemsViewModel>{
        return RetrofitInstance.api.getAllTeachers()
    }

    //getting students from backend

    suspend fun postClassInfo(post: presentiClassData):Response<ItemsViewModelStudents>{
        return RetrofitInstance.api.postClassInfo(post)
    }

    suspend fun postPresentiClassInfo(post: presentiClassInfo):Response<presentiClassIDresponse>{
        return RetrofitInstance.api.postPresentiClassInfo(post)
    }

    suspend fun setPresenti(post: setPresentData):Response<presentiClassIDresponse>{
        return RetrofitInstance.api.setPresenti(post)
    }

    suspend fun pushRollNumber(post: totalAttendanceData):Response<totalAttendanceDataResponse>{
        return RetrofitInstance.api.pushRollNumber(post)
    }

    suspend fun sendPresentiData(post: presentiCheckData):Response<RollNumbers>{
        return RetrofitInstance.api.sendPresentiData(post)
    }

    //delete teacher route
    suspend fun sendTeacherId(post: PostTeacherId):Response<DeleteTeacherResponse>{
        return RetrofitInstance.api.sendTeacherId(post)
    }

}