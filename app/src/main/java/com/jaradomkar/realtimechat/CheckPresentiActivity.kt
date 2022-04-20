package com.jaradomkar.realtimechat

import CustomAdapterStudent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaradomkar.realtimechat.model.presentiCheckData
import com.jaradomkar.realtimechat.repository.Repository

class CheckPresentiActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var getPresentiBtn:Button
    private lateinit var backArrow: ImageView
    private lateinit var studentClass:EditText
    private lateinit var presentiTime: EditText
    private lateinit var studentYear:EditText
    private lateinit var studentSubject:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_presenti)

        backArrow=findViewById(R.id.back_arrow)
        getPresentiBtn = findViewById(R.id.get_presenti)
        studentClass=findViewById(R.id.teacher_class)
        studentYear=findViewById(R.id.teacher_year)
        studentSubject=findViewById(R.id.teacher_subject)
        presentiTime=findViewById(R.id.presenti_time)

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        getPresentiBtn.setOnClickListener{


            val branch = studentClass.text.toString()
            val year = studentYear.text.toString().toInt()
            val subject = studentSubject.text.toString()
            val DayTime = presentiTime.text.toString()

            val data = presentiCheckData(branch, year, subject,DayTime)
            viewModel.sendPresentiData(data)


        }

        viewModel.presentiCheckDataResponseCheck.observe(this){response->
            if(response.isSuccessful){

                val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(this)

                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapterStudent(this,response.body()?.attendance!!)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

            }else{
                Toast.makeText(this,"ERROR", Toast.LENGTH_LONG).show()
            }
        }

        backArrow.setOnClickListener{
            val intent = Intent(this, TeacherActivity::class.java);
            startActivity(intent)
        }

    }
}