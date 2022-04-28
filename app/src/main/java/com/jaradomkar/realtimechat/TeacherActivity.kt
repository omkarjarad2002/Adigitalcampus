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
import com.jaradomkar.realtimechat.model.teacherInfoData
import com.jaradomkar.realtimechat.model.userInfoData
import com.jaradomkar.realtimechat.repository.Repository

class TeacherActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    private lateinit var backArrow:ImageView
    private lateinit var takePresenti: Button
    private lateinit var changePassword: Button
    private lateinit var checkPresentiBtn: Button
    private lateinit var takePresntiBtn: Button
    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPhone: EditText
    private lateinit var editDepartment: EditText
    private lateinit var editYear: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)


        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java);


        val teacherData = teacherInfoData(intent.getStringExtra("email").toString())
        viewModel.pushEmailForTeacherInfo(teacherData)

        backArrow=findViewById(R.id.back_arrow)
        takePresenti=findViewById(R.id.take_presenti)
        changePassword=findViewById(R.id.user_change_password)
        editName=findViewById(R.id.edit_name)
        editEmail=findViewById(R.id.edit_email)
        editPhone=findViewById(R.id.edit_phone)
        editDepartment=findViewById(R.id.edit_branch)
        editYear=findViewById(R.id.edit_year)
        takePresntiBtn=findViewById(R.id.take_presenti)
        checkPresentiBtn=findViewById(R.id.check_presenti)

        backArrow.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        checkPresentiBtn.setOnClickListener{
            val intent = Intent(this,CheckPresentiActivity::class.java);
            startActivity(intent)
        }

        changePassword.setOnClickListener{
            val intent=Intent(this,ChangePasswordActivity::class.java);
            startActivity(intent)
        }

        takePresntiBtn.setOnClickListener{
            val intent=Intent(this,Presentiboard::class.java)
            startActivity(intent)
        }

        viewModel.teacherInfoResponse.observe(this) { response ->
            if (response.isSuccessful) {


                if(response.body() !==null) {
                    editName.setText(response.body()?.TeacherInfo!!.name.toString())
                    editEmail.setText(response.body()?.TeacherInfo!!.email.toString())
                    editPhone.setText(response.body()?.TeacherInfo!!.phone.toString())
                    editDepartment.setText(response.body()?.TeacherInfo!!.department.toString())
                    editYear.setText(response.body()?.TeacherInfo!!.year.toString())

                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}