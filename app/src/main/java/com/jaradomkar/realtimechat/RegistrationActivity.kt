package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.LoginData
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.model.RegisterData
import com.jaradomkar.realtimechat.repository.Repository

class RegistrationActivity : AppCompatActivity() {

    private lateinit var savebutton : Button
    private lateinit var viewModel:MainViewModel
    private lateinit var editName:EditText
    private lateinit var editEmail:EditText
    private lateinit var editPhone:EditText
    private lateinit var editBranch:EditText
    private lateinit var editYear:EditText
    private lateinit var editRollNumber:EditText
    private lateinit var backArrow:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        savebutton = findViewById(R.id.save_btn)

        editName = findViewById(R.id.edit_name)
        editEmail = findViewById(R.id.edit_email)
        editPhone = findViewById(R.id.edit_phone)
        editBranch = findViewById(R.id.edit_branch)
        editYear = findViewById(R.id.edit_year)
        editRollNumber = findViewById(R.id.edit_rollnumber)
        backArrow = findViewById(R.id.back_arrow)



        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@RegistrationActivity,viewModelFactory).get(MainViewModel::class.java)

        savebutton.setOnClickListener{
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val phone = editPhone.text.toString().toLong()
            val branch = editBranch.text.toString()
            val year = editYear.text.toString().toInt()
            val rollNumber = editRollNumber.text.toString()

            val data = RegisterData(name,email,phone,branch,year,rollNumber)
            viewModel.pushRegisterData(data)
        }

        backArrow.setOnClickListener{
            val intent = Intent(this@RegistrationActivity,ProfileActivity::class.java);
            startActivity(intent)
        }

        viewModel.registerResponse.observe(this@RegistrationActivity) { response ->
            if (response.isSuccessful) {
                val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                startActivity(intent)

                Toast.makeText(this@RegistrationActivity, "User registration successful !", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this@RegistrationActivity, "Sorry, we failed to register your info !", Toast.LENGTH_LONG).show()
            }
        }

    }
}