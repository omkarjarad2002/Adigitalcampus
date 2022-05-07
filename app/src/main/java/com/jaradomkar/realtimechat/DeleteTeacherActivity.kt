package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.model.PostTeacherId
import com.jaradomkar.realtimechat.repository.Repository

class DeleteTeacherActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var backArrow:ImageView
    private lateinit var teacherEmail:EditText
    private lateinit var deleteTeacher:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_teacher)

        backArrow=findViewById(R.id.back_arrow)
        teacherEmail=findViewById(R.id.edit_email)
        deleteTeacher=findViewById(R.id.delete_btn)

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel = ViewModelProvider(this@DeleteTeacherActivity,viewModelFactory).get(MainViewModel::class.java)

        backArrow.setOnClickListener{
            val intent = Intent(this,ManagementActivity::class.java)
            startActivity(intent)
        }

        deleteTeacher.setOnClickListener{
            val email = teacherEmail.text.toString()

            val data = PostTeacherId(email)
            viewModel.sendTeacherId(data)
        }

        viewModel.deleteTeacherResponse.observe(this){response->
            if(response.isSuccessful){
                val intent = Intent(this,ManagementActivity::class.java)
                Toast.makeText(applicationContext,"Teacher removed successfully !!",Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"Sorry, we are enable to remove teacher !!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}