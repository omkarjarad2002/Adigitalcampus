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

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val loginResponse: MutableLiveData<Response<LoginResponce>> = MutableLiveData()
    val registerResponse: MutableLiveData<Response<RegisterData>> = MutableLiveData()
    val emailVerificationResponse: MutableLiveData<Response<EmailVerificationOtpResponse>> = MutableLiveData()
    val changedPassResponse: MutableLiveData<Response<ChangedPasswordResponse>> = MutableLiveData()

    fun pushPost(post:Post){
        viewModelScope.launch {
            val response: Response<Post> = repository.pushPost(post)
            myResponse.value = response
        }
    }
    fun pushLoginData(post:LoginData){
        viewModelScope.launch {
            val response: Response<LoginResponce> = repository.pushLoginData(post)
            loginResponse.value = response
        }
    }

    fun pushEmailVerification(post:EmailVerificationData){
        viewModelScope.launch {
            val response: Response<EmailVerificationOtpResponse> = repository.pushEmailVerification(post)
            emailVerificationResponse.value = response
        }
    }

    fun getPost(){
        viewModelScope.launch {
            val response: Response<Post> = repository.getPost()
            myResponse2.value = response
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

}