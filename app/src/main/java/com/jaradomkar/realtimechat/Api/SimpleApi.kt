package com.jaradomkar.realtimechat.Api

import com.jaradomkar.realtimechat.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {



    @GET("refreshtoken")
    suspend fun getRefresh(
        @Header("authorization") Authorisation:String
    ): Response<tokenResponse>

    @GET("getAllTeachers")
    suspend fun getAllTeachers(
    ): Response<ItemsViewModel>

    @POST("getStudent")
    suspend fun postClassInfo(
        @Body post: presentiClassData
    ): Response<ItemsViewModelStudents>

    @POST("presentUpsent")
    suspend fun setPresenti(
        @Body post: setPresentData
    ): Response<presentiClassIDresponse>

    @POST("presentiInfo")
    suspend fun postPresentiClassInfo(
        @Body post: presentiClassInfo
    ): Response<presentiClassIDresponse>

    @POST("signup")
    suspend fun pushPost(
        @Body post: Post
    ): Response<signUpResponse>

    @POST("getUserProfileInfo")
    suspend fun pushEmailForUserInfo(
        @Body post: userInfoData
    ): Response<RegisterResponse>

    @POST("getOneTeacher")
    suspend fun pushEmailForTeacherInfo(
        @Body post: teacherInfoData
    ): Response<teacherResponse>

    @POST("emailSendForOtp")
    suspend fun pushEmailVerification(
        @Body post: EmailVerificationData
    ): Response<EmailVerificationOtpResponse>

    @POST("emailSendForSignUpOtp")
    suspend fun pushEmailVerificationForSignUp(
        @Body post: EmailVerificationDataForSignUp
    ): Response<EmailVerificationSignUpOtpResponse>

    @POST("changePassword")
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

    @POST("addTeacher")
    suspend fun pushTeacherData(
        @Body post: RegisterTeacher
    ):Response<RegisterTeacherResponse>

    @POST("getTotalAttendance")
    suspend fun pushRollNumber(
        @Body post: totalAttendanceData
    ):Response<totalAttendanceDataResponse>

    @POST("getDayPresenti")
    suspend fun sendPresentiData(
        @Body post: presentiCheckData
    ):Response<RollNumbers>

    //making request to delete teacher
    @POST("deleteTeacher")
    suspend fun sendTeacherId(
        @Body post: PostTeacherId
    ):Response<DeleteTeacherResponse>

}