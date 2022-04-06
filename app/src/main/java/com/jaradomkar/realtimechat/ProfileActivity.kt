package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity() {

    private lateinit var backArrow:ImageView
    private lateinit var changePassword:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        backArrow=findViewById(R.id.back_arrow)
        changePassword=findViewById(R.id.user_change_password)

        backArrow.setOnClickListener{
            val intent = Intent(this@ProfileActivity,MainActivity::class.java);
            startActivity(intent)
        }

        changePassword.setOnClickListener{
            val intent = Intent(this@ProfileActivity,ChangePasswordActivity::class.java);
            startActivity(intent)
        }


    }
}