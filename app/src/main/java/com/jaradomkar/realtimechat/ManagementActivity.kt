package com.jaradomkar.realtimechat

import CustomAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jaradomkar.realtimechat.model.ItemsViewModel
import com.jaradomkar.realtimechat.model.getTeachers
import com.jaradomkar.realtimechat.repository.Repository

class ManagementActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var addTBtn:Button
    private lateinit var backArrow:ImageView
    private lateinit var deleteTeacher:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)

        addTBtn = findViewById(R.id.add_teacher)
        backArrow = findViewById(R.id.back_arrow)
        deleteTeacher = findViewById(R.id.tv_delete_teacher)

        addTBtn.setOnClickListener {
            val intent = Intent(this, AddTeacherActivity::class.java)
            startActivity(intent)
        }

        backArrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        deleteTeacher.setOnClickListener{
            val intent = Intent(this,DeleteTeacherActivity::class.java)
            startActivity(intent)
        }

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel= ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        viewModel.getAllTeachers()

        viewModel.teachersResponse.observe(this){response->
            if(response.isSuccessful){

                val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(this)

                // This will pass the ArrayList to our Adapter
                val adapter = CustomAdapter(this,response.body()?.getTeachers!!)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

            }
        }




    }


}