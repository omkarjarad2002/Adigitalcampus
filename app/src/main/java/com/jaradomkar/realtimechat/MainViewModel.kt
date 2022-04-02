package com.jaradomkar.realtimechat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaradomkar.realtimechat.model.LoginData
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.model.RegisterData
import com.jaradomkar.realtimechat.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val loginResponse: MutableLiveData<Response<LoginData>> = MutableLiveData()
    val registerResponse: MutableLiveData<Response<RegisterData>> = MutableLiveData()

    fun pushPost(post:Post){
        viewModelScope.launch {
            val response: Response<Post> = repository.pushPost(post)
            myResponse.value = response
        }
    }
    fun pushLoginData(data: LoginData){
        viewModelScope.launch {
            val response: Response<LoginData> = repository.pushLoginData(data)
            loginResponse.value = response
        }
    }

    fun getPost(){
        viewModelScope.launch {
            val response: Response<Post> = repository.getPost()
            myResponse2.value = response
        }
    }

    fun pushRegisterData(data: RegisterData){
        viewModelScope.launch {
            val response: Response<RegisterData> = repository.pushRegisterData(data)
            registerResponse.value = response
        }
    }

}