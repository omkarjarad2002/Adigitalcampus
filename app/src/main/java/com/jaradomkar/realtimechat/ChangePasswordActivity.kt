package com.jaradomkar.realtimechat

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
import com.jaradomkar.realtimechat.repository.Repository

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var sentotpBtn:Button
    private lateinit var backArrow:ImageView
    private lateinit var sendOtp:Button
    private lateinit var editEmail:EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        sentotpBtn=findViewById(R.id.send_otp)
        backArrow=findViewById(R.id.back_arrow)
        sendOtp=findViewById(R.id.send_otp)
        editEmail=findViewById(R.id.edit_email)


        backArrow.setOnClickListener{
            val intent = Intent(this@ChangePasswordActivity,MainActivity::class.java);
            startActivity(intent)
        }

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel=ViewModelProvider(this@ChangePasswordActivity,viewModelFactory).get(MainViewModel::class.java)

        sendOtp.setOnClickListener{
            val email = editEmail.text.toString()

            val data = EmailVerificationData(email)

            viewModel.pushEmailVerification(data)
        }


        viewModel.emailVerificationResponse.observe(this@ChangePasswordActivity){response->
            if(response.isSuccessful){

                Log.e("Respose",response.body()?.final__otp!!.toString())

                Toast.makeText(applicationContext,response.body()?.final__otp!!.toString(),Toast.LENGTH_LONG).show()

                val intent = Intent(this@ChangePasswordActivity,ChangePasswordSuccessActivity::class.java);
                intent.putExtra("email",editEmail.text)
                intent.putExtra("otp_code",response.body()?.final__otp)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"failure !",Toast.LENGTH_LONG).show()
            }
        }

    }
}