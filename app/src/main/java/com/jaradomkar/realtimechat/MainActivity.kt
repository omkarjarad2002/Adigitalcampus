package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.jaradomkar.realtimechat.model.Post
import com.jaradomkar.realtimechat.repository.Repository
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this@MainActivity,viewModelFactory).get(MainViewModel::class.java)

       viewModel.getPost()

        viewModel.myResponse2.observe(this@MainActivity){
            response->if(response.isSuccessful){
                Toast.makeText(this,response.toString(),Toast.LENGTH_SHORT).show()
        }
        }


        //set navigation menu code
        val drawerLayout:DrawerLayout=findViewById(R.id.drawerLayout)
        val navView:NavigationView=findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home ->{
                    val intent = Intent(this@MainActivity,MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_event ->{
                    val intent = Intent(this@MainActivity,ScrollingActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_login ->{
                    val intent = Intent(this@MainActivity,Login::class.java)
                    startActivity(intent)
                }
                R.id.nav_delete ->{
                    val intent = Intent(this@MainActivity,SignUp::class.java)
                    startActivity(intent)
                }
                R.id.nav_setting -> {
                    val intent = Intent(this@MainActivity,RegistrationActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}