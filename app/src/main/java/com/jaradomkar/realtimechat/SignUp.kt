package com.jaradomkar.realtimechat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.EmailVerificationData
import com.jaradomkar.realtimechat.model.EmailVerificationDataForSignUp
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.repository.Repository

class SignUp : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editCPassword:EditText
    private lateinit var signupButtonR:Button
    private lateinit var backArrow:ImageView
    private lateinit var sendOtp:Button
    private lateinit var editOtp:EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editEmail = findViewById(R.id.edit_email)
        editPassword = findViewById(R.id.edit_password)
        editCPassword = findViewById(R.id.edit_cpassword)
        signupButtonR = findViewById(R.id.edit_signup_btn_student)
        backArrow = findViewById(R.id.back_arrow)
        sendOtp = findViewById(R.id.send_otp)
        editOtp=findViewById(R.id.edit_otp)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@SignUp,viewModelFactory).get(MainViewModel::class.java)


        //seding email verification otp
        sendOtp.setOnClickListener{
            val email = editEmail.text.toString()
            Log.e("Email",email)

            val data = EmailVerificationDataForSignUp(email)

            viewModel.pushEmailVerificationForSignUp(data)
        }

        //checking email verification response
        viewModel.emailVerificationResponseForSignUp.observe(this){response->
            if(response.isSuccessful){

                Log.e("Respose",response.body()?.final__otp!!.toString())

                signupButtonR.setOnClickListener{

                    val otp = editOtp.text.toString()
                    val email = editEmail.text.toString()
                    val password = editPassword.text.toString()
                    val cpassword = editCPassword.text.toString()

                    val data = Post(otp,response.body()?.final__otp!!.toString(),email,password,cpassword)
                    viewModel.pushPost(data)
                }

            }else{
                Toast.makeText(applicationContext,"failure !",Toast.LENGTH_LONG).show()
            }
        }


        backArrow.setOnClickListener{
            val intent = Intent(this@SignUp,MainActivity::class.java)
            startActivity(intent)
        }

        viewModel.myResponse.observe(this@SignUp) { responce ->
            if (responce.isSuccessful) {

                val sharedPref = getSharedPreferences( "ACCESS_TOKEN", Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putString("ACCESS_TOKEN", responce.body()?.token!!.toString())
                    apply()
                }

                val intent = Intent(this@SignUp, MainActivity::class.java)
                startActivity(intent)

                Toast.makeText(this@SignUp, "You have successful signup", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this@SignUp, "Sorry, we failed to make your account !", Toast.LENGTH_LONG).show()
            }
        }

        }
    }

