package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.ChangePasswordData
import com.jaradomkar.realtimechat.model.ChangedPasswordResponse
import com.jaradomkar.realtimechat.repository.Repository

class ChangePasswordSuccessActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var backArrow:ImageView
    private lateinit var editEmail:EditText
    private lateinit var editOtp:EditText
    private lateinit var editPassword:EditText
    private lateinit var editCPassword:EditText
    private lateinit var changePassword:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password_success)

        backArrow=findViewById(R.id.back_arrow)
        editEmail=findViewById(R.id.edit_email)
        editOtp=findViewById(R.id.edit_otp)
        editPassword=findViewById(R.id.edit_password)
        editCPassword=findViewById(R.id.edit_cpassword)
        changePassword=findViewById(R.id.change_password)


        backArrow.setOnClickListener{
            val intent = Intent(this@ChangePasswordSuccessActivity,ChangePasswordActivity::class.java);
            startActivity(intent)
        }

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@ChangePasswordSuccessActivity,viewModelFactory).get(MainViewModel::class.java)


        changePassword.setOnClickListener{
            val email = editEmail.text.toString()
            val otp = editOtp.text.toString()
            val password = editPassword.text.toString()
            val cpassword = editCPassword.text.toString()

            val data = ChangePasswordData(otp,intent.getStringExtra("otp_code").toString(),email,password,cpassword);

            viewModel.pushChangedPassword(data)
        }


        viewModel.changedPassResponse.observe(this@ChangePasswordSuccessActivity){response->
            if(response.isSuccessful){
                Toast.makeText(applicationContext, "Password Changed Successfully !",Toast.LENGTH_LONG).show()
                val intent = Intent(this@ChangePasswordSuccessActivity,Login::class.java);
                startActivity(intent);
            }else{
                Toast.makeText(applicationContext,"Sorry,we failed to change your password !",Toast.LENGTH_LONG).show()
            }
        }

    }
}