package com.jaradomkar.realtimechat

import CustomAdapterTeachers
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaradomkar.realtimechat.model.*
import com.jaradomkar.realtimechat.repository.Repository
import kotlin.properties.Delegates

class Presentiboard : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var PresentiBtn:Button
    private lateinit var SavePresentiBtn:Button
    private lateinit var backArrow:ImageView
    private lateinit var studentClass:EditText
    private lateinit var presentiTime: EditText
    private lateinit var studentYear:EditText
    private lateinit var studentSubject:EditText
    private var rollNumberArray:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentiboard)
        PresentiBtn = findViewById(R.id.take_students)
        backArrow=findViewById(R.id.back_arrow)
        studentClass=findViewById(R.id.teacher_class)
        studentYear=findViewById(R.id.teacher_year)
        studentSubject=findViewById(R.id.teacher_subject)
        SavePresentiBtn=findViewById(R.id.save_presenti)
        presentiTime=findViewById(R.id.presenti_time)

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        PresentiBtn.setOnClickListener{

            val branch = studentClass.text.toString()
            val year = studentYear.text.toString().toInt()
            val subject = studentSubject.text.toString()
            val DayTime = presentiTime.text.toString()

            val data = presentiClassData(branch, year, subject)
            val Info = presentiClassInfo(branch, year, subject,DayTime)
            viewModel.postClassInfo(data)
            viewModel.postPresentiClassInfo(Info)

            viewModel.studentsResponse.observe(this){response->
                if(response.isSuccessful){
                    if(response.isSuccessful){

                        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

                        // this creates a vertical layout Manager
                        recyclerview.layoutManager = LinearLayoutManager(this)

                        // This will pass the ArrayList to our Adapter
                        val adapter = CustomAdapterTeachers(this,response.body()?.students!!)

                        // Setting the Adapter with the recyclerview
                        recyclerview.adapter = adapter

                        adapter.setOnItemClickListener(object :CustomAdapterTeachers.onItemClickListener{
                            override fun onItemClick(position: Int) {

                                for (i in 0 until 1) {
                                    Toast.makeText(applicationContext,response.body()?.students!![position].rollNumber.toString(),Toast.LENGTH_SHORT).show()
                                    rollNumberArray.add(response.body()?.students!![position].rollNumber.toString())
                                }

                            }
                        })
                    }
                }
            }
        }

        backArrow.setOnClickListener{
            val intent = Intent(this,TeacherActivity::class.java);
            startActivity(intent)
        }

        SavePresentiBtn.setOnClickListener{

            viewModel.presentiClassInfoResponse.observe(this){response->
                if(response.isSuccessful) {

                    val DayTime = presentiTime.text.toString()

                    val data = setPresentData(response.body()?._id.toString(),DayTime,rollNumberArray)
                    viewModel.setPresenti(data)

                    Toast.makeText(applicationContext,"Presenti Saved !",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}