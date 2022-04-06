package com.jaradomkar.realtimechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_management)
    }
}