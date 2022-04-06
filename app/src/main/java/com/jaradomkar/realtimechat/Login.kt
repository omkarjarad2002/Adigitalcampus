package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.LoginData
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.repository.Repository
import kotlin.math.log

class Login : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var editEmail:EditText
    private lateinit var editPassword:EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var forgotPassword:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editEmail = findViewById(R.id.login_email)
        editPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_btn)
        signupButton = findViewById(R.id.signup_btn)
        forgotPassword = findViewById(R.id.forgot_password)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@Login,viewModelFactory).get(MainViewModel::class.java)

        loginButton.setOnClickListener{
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val data = LoginData(email,password)

            viewModel.pushLoginData(data)
//            Toast.makeText(this,data.toString(),Toast.LENGTH_LONG).show()
        }

        forgotPassword.setOnClickListener{
            val intent = Intent(this@Login,ChangePasswordActivity::class.java);
            startActivity(intent)
        }



        viewModel.loginResponse.observe(this@Login) { response ->
            if (response.isSuccessful) {

//                Log.d("LoginRes",response.body()?.user!!.email)
//                Log.d("LoginRes",response.body()?.user!!.isadmin.toString())
//                Log.d("LoginRes",response.body()?.user!!.isteacher.toString())

                if(response.body()?.user!!.isadmin==true){

                    val intent = Intent(this@Login, ManagementActivity::class.java)
                    startActivity(intent)
                }else if(response.body()?.user!!.isteacher==true){
                    val intent = Intent(this@Login,TeacherActivity::class.java)
                    startActivity(intent)
                }else{

                    val intent = Intent(this@Login,ProfileActivity::class.java)
                    startActivity(intent)

                }

            }
        }

        signupButton.setOnClickListener{
            val intent = Intent(this@Login,SignUp::class.java)
            startActivity(intent)
        }

    }
}