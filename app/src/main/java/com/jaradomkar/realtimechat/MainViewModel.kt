package com.jaradomkar.realtimechat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaradomkar.realtimechat.model.*
import com.jaradomkar.realtimechat.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    val myResponse: MutableLiveData<Response<signUpResponse>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val loginResponse: MutableLiveData<Response<LoginResponce>> = MutableLiveData()
    val registerResponse: MutableLiveData<Response<RegisterData>> = MutableLiveData()
    val emailVerificationResponse: MutableLiveData<Response<EmailVerificationOtpResponse>> = MutableLiveData()
    val emailVerificationResponseForSignUp: MutableLiveData<Response<EmailVerificationSignUpOtpResponse>> = MutableLiveData()
    val userDataResponse: MutableLiveData<Response<RegisterResponse>> = MutableLiveData()
    val changedPassResponse: MutableLiveData<Response<ChangedPasswordResponse>> = MutableLiveData()
    val teacherRegisterResponse: MutableLiveData<Response<RegisterTeacherResponse>> = MutableLiveData()
    val teacherInfoResponse: MutableLiveData<Response<teacherResponse>> = MutableLiveData()
    val refreshTokenResponse: MutableLiveData<Response<tokenResponse>> = MutableLiveData()
    val teachersResponse: MutableLiveData<Response<ItemsViewModel>> = MutableLiveData()
    val studentsResponse: MutableLiveData<Response<ItemsViewModelStudents>> = MutableLiveData()
    val presentiClassInfoResponse: MutableLiveData<Response<presentiClassIDresponse>> = MutableLiveData()
    val totalAttendanceDataResponse: MutableLiveData<Response<totalAttendanceDataResponse>> = MutableLiveData()
    val presentiCheckDataResponseCheck: MutableLiveData<Response<RollNumbers>> = MutableLiveData()
    val deleteTeacherResponse: MutableLiveData<Response<DeleteTeacherResponse>> = MutableLiveData()

    fun pushPost(post:Post){
        viewModelScope.launch {
            val response: Response<signUpResponse> = repository.pushPost(post)
            myResponse.value = response
        }
    }

    fun pushLoginData(post:LoginData){
        viewModelScope.launch {
            val response: Response<LoginResponce> = repository.pushLoginData(post)
            loginResponse.value = response
        }
    }

    fun pushEmailForUserInfo(post:userInfoData){
        viewModelScope.launch {
            val response: Response<RegisterResponse> = repository.pushEmailForUserInfo(post)
            userDataResponse.value = response
        }
    }

    fun pushEmailForTeacherInfo(post:teacherInfoData){
        viewModelScope.launch {
            val response: Response<teacherResponse> = repository.pushEmailForTeacherInfo(post)
            teacherInfoResponse.value = response
        }
    }

    fun pushEmailVerification(post:EmailVerificationData){
        viewModelScope.launch {
            val response: Response<EmailVerificationOtpResponse> = repository.pushEmailVerification(post)
            emailVerificationResponse.value = response
        }
    }

    fun pushEmailVerificationForSignUp(post:EmailVerificationDataForSignUp){
        viewModelScope.launch {
            val response: Response<EmailVerificationSignUpOtpResponse> = repository.pushEmailVerificationForSignUp(post)
            emailVerificationResponseForSignUp.value = response
        }
    }

    fun pushRegisterData(post:RegisterData){
        viewModelScope.launch {
            val response: Response<RegisterData> = repository.pushRegisterData(post)
            registerResponse.value = response
        }
    }

    fun pushChangedPassword(post:ChangePasswordData){
        viewModelScope.launch {
            val response: Response<ChangedPasswordResponse> = repository.pushChangedPassword(post)
            changedPassResponse.value = response
        }
    }

    fun pushTeacherData(post: RegisterTeacher){
        viewModelScope.launch {
            val response: Response<RegisterTeacherResponse> = repository.pushTeacherData(post)
            teacherRegisterResponse.value = response
        }
    }

    //getting refresh token response

    fun getRefresh(token:String){
        viewModelScope.launch {
            val response: Response<tokenResponse> = repository.getRefresh(token)
            refreshTokenResponse.value = response
        }
    }

 //getting teachers response

    fun getAllTeachers(){
        viewModelScope.launch {
            val response: Response<ItemsViewModel> = repository.getAllTeachers()
            teachersResponse.value = response
        }
    }
 //getting students response

    fun postClassInfo(post: presentiClassData){
        viewModelScope.launch {
            val response: Response<ItemsViewModelStudents> = repository.postClassInfo(post)
            studentsResponse.value = response
        }
    }
    //getting students response

    fun postPresentiClassInfo(post: presentiClassInfo){
        viewModelScope.launch {
            val response: Response<presentiClassIDresponse> = repository.postPresentiClassInfo(post)
            presentiClassInfoResponse.value = response
        }
    }

    fun setPresenti(post: setPresentData){
        viewModelScope.launch {
            val response: Response<presentiClassIDresponse> = repository.setPresenti(post)
            presentiClassInfoResponse.value = response
        }
    }

    fun pushRollNumber(post: totalAttendanceData){
        viewModelScope.launch {
            val response: Response<totalAttendanceDataResponse> = repository.pushRollNumber(post)
            totalAttendanceDataResponse.value = response
        }
    }

    fun sendPresentiData(post: presentiCheckData){
        viewModelScope.launch {
            val response: Response<RollNumbers> = repository.sendPresentiData(post)
            presentiCheckDataResponseCheck.value = response
        }
    }

    fun sendTeacherId(post: PostTeacherId){
        viewModelScope.launch {
            val response: Response<DeleteTeacherResponse> = repository.sendTeacherId(post)
            deleteTeacherResponse.value = response
        }
    }


}