package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.LoginData
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.repository.Repository

class Login : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var editEmail:EditText
    private lateinit var editPassword:EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.login_email)
        editPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_btn)
        signupButton = findViewById(R.id.signup_btn)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@Login,viewModelFactory).get(MainViewModel::class.java)

        loginButton.setOnClickListener{
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val data = LoginData(email,password)

            viewModel.pushLoginData(data)

            val intent =Intent(this@Login,MainActivity::class.java)
            startActivity(intent)
        }


        signupButton.setOnClickListener{
            val intent = Intent(this@Login,SignUp::class.java)
            startActivity(intent)
        }

    }
}