package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jaradomkar.realtimechat.R
import com.jaradomkar.realtimechat.model.RegisterTeacher
import com.jaradomkar.realtimechat.repository.Repository

class AddTeacherActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var editName:EditText
    private lateinit var editEmail:EditText
    private lateinit var editPhone:EditText
    private lateinit var editDepartment:EditText
    private lateinit var editYear:EditText
    private lateinit var editPassword:EditText
    private lateinit var editCPassword:EditText
    private lateinit var addBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_teacher)

        editName=findViewById(R.id.edit_name);
        editEmail=findViewById(R.id.edit_email);
        editPhone=findViewById(R.id.edit_phone);
        editDepartment=findViewById(R.id.edit_department);
        editYear=findViewById(R.id.edit_year);
        editPassword=findViewById(R.id.edit_password);
        editCPassword=findViewById(R.id.edit_cpassword);
        addBtn=findViewById(R.id.add_btn);

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this@AddTeacherActivity,viewModelFactory).get(MainViewModel::class.java)

        addBtn.setOnClickListener{
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val phone = editPhone.text.toString().toLong()
            val department = editDepartment.text.toString()
            val year = editYear.text.toString()
            val password = editPassword.text.toString()
            val cpassword = editCPassword.text.toString()

            val data = RegisterTeacher(name,email,phone,department,year,password,cpassword)
            viewModel.pushTeacherData(data)
        }

        viewModel.teacherRegisterResponse.observe(this){response->
            if(response.isSuccessful){

                //adding component of toast for success message

                MaterialAlertDialogBuilder(this)
                    .setTitle("Success")
                    .setMessage("Teacher registration Successful")
                    .setPositiveButton("ok") { dialog, which ->
                        // Respond to positive button press
                        dialog.dismiss()
                    }
                    .show()


                Toast.makeText(applicationContext,"Teacher Added Successfully!",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,ManagementActivity::class.java);
                startActivity(intent)
            }else{

                //adding component of toast for success message

//                MaterialAlertDialogBuilder(this)
//                    .setTitle("Error")
//                    .setMessage("Error occurred during registration")
//                    .setPositiveButton("ok") { dialog, which ->
//                        // Respond to positive button press
//                        dialog.dismiss()
//                    }
//                    .show()


                Toast.makeText(applicationContext,"Sorry, we failed to add new teacher !",Toast.LENGTH_SHORT).show()
            }
        }
    }
}