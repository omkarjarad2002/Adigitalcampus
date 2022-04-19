package com.jaradomkar.realtimechat

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.jaradomkar.realtimechat.repository.Repository

class SplashActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel=ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)


        val sharedPref = getSharedPreferences("ACCESS_TOKEN",Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("ACCESS_TOKEN","");
        Log.d("SRTOKEN",token!!.toString())

        //MAKE GET REQUEST FROM HERE
        viewModel.getRefresh(token);

        viewModel.refreshTokenResponse.observe(this){response->

            if(response.isSuccessful){
                val intent = Intent(this,MainActivity::class.java)
                Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
                startActivity(intent)
            }else{
                val intent = Intent(this,Login::class.java)
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }


        @Suppress("DEPRECATION")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }



    }
}