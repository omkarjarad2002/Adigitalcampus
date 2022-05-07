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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jaradomkar.realtimechat.model.EmailVerificationData
import com.jaradomkar.realtimechat.repository.Repository

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var sentotpBtn: Button
    private lateinit var backArrow: ImageView
    private lateinit var sendOtp: Button
    private lateinit var editEmail: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        sentotpBtn = findViewById(R.id.send_otp)
        backArrow = findViewById(R.id.back_arrow)
        sendOtp = findViewById(R.id.send_otp)
        editEmail = findViewById(R.id.edit_email)


        backArrow.setOnClickListener {
            val intent = Intent(this@ChangePasswordActivity, Login::class.java);
            startActivity(intent)
        }

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel = ViewModelProvider(
            this@ChangePasswordActivity,
            viewModelFactory
        ).get(MainViewModel::class.java)

        sendOtp.setOnClickListener {
            val email = editEmail.text.toString()

            val data = EmailVerificationData(email)

            viewModel.pushEmailVerification(data)
        }


        viewModel.emailVerificationResponse.observe(this@ChangePasswordActivity) { response ->
            if (response.isSuccessful) {

                //adding component of toast for success message

//                MaterialAlertDialogBuilder(this)
//                    .setTitle("Success")
//                    .setMessage("Login Successful")
//                    .setPositiveButton("ok") { dialog, which ->
//                        // Respond to positive button press
//                        dialog.dismiss()
//                    }
//                    .show()

                Toast.makeText(
                    applicationContext,
                    "Otp successfully send to your registered emailId ",
                    Toast.LENGTH_SHORT
                ).show()

                val intent =
                    Intent(this@ChangePasswordActivity, ChangePasswordSuccessActivity::class.java);
                intent.putExtra("email", editEmail.text)
                intent.putExtra("otp_code", response.body()?.final__otp)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "failure !", Toast.LENGTH_SHORT).show()
            }
        }

    }
}