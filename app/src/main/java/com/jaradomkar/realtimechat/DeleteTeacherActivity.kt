package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class DeleteTeacherActivity : AppCompatActivity() {

    private lateinit var backArrow:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_teacher)

        backArrow=findViewById(R.id.back_arrow)

        backArrow.setOnClickListener{
            val intent = Intent(this,TeacherActivity::class.java)
            startActivity(intent)
        }

    }
}