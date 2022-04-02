package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.repository.Repository

class SignUp : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editCPassword:EditText
    private lateinit var signupButtonR:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editEmail = findViewById(R.id.edit_email)
        editPassword = findViewById(R.id.edit_password)
        editCPassword = findViewById(R.id.edit_cpassword)
        signupButtonR = findViewById(R.id.edit_signup_btn_student)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@SignUp,viewModelFactory).get(MainViewModel::class.java)

        signupButtonR.setOnClickListener{

            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val cpassword = editCPassword.text.toString()

            val data = Post(email,password,cpassword)
            viewModel.pushPost(data)

        }

        viewModel.myResponse.observe(this@SignUp) { responce ->
            if (responce.isSuccessful) {
                Toast.makeText(this@SignUp, responce.toString(), Toast.LENGTH_LONG).show()
                val intent = Intent(this@SignUp, RegistrationActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this@SignUp, responce.toString(), Toast.LENGTH_LONG).show()
            }
        }

        }
    }

