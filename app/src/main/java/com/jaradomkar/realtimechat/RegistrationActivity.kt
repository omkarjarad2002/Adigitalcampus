package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.LoginData
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

        savebutton.setOnClickListener{
            val intent = Intent(this@RegistrationActivity,MainActivity::class.java)
            startActivity(intent)
        }

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@RegistrationActivity,viewModelFactory).get(MainViewModel::class.java)

        savebutton.setOnClickListener{
//            val name = editName.text.toString()
//            val email = editEmail.text.toString()
//            val phone = editPhone.text.toString().toLong()
//            val branch = editBranch.text.toString()
//            val year = editYear.text.toString().toInt()
//
//            viewModel.pushRegisterData(data = RegisterData(name,email,phone,branch,year))

            val intent =Intent(this@RegistrationActivity,MainActivity::class.java)
            startActivity(intent)
        }

    }
}