package com.jaradomkar.realtimechat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
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


        //set navigation menu code
        val drawerLayout:DrawerLayout=findViewById(R.id.drawerLayout)
        val navView:NavigationView=findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        val sharedPref = getSharedPreferences("ACCESS_TOKEN",Context.MODE_PRIVATE) ?: return
        val token = sharedPref.getString("ACCESS_TOKEN","");


        navView.setNavigationItemSelectedListener {
            when(it.itemId){

                R.id.profile_image ->{

                    //MAKE GET REQUEST FROM HERE
                    viewModel.getRefresh(token.toString());

                    viewModel.refreshTokenResponse.observe(this){response->

                        if (response.body()?.tokenUser!!.isadmin == true) {

                            val intent = Intent(this, ManagementActivity::class.java)
                            startActivity(intent)
                        } else if (response.body()?.tokenUser!!.isteacher == true) {
                            val intent = Intent(this, TeacherActivity::class.java)
                            intent.putExtra("email",response.body()?.tokenUser!!.email!!.toString())
                            startActivity(intent)
                        } else {
                            val intent = Intent(this, ProfileActivity::class.java)
                            intent.putExtra("email",response.body()?.tokenUser!!.email!!.toString())

                            startActivity(intent)

                        }
                    }
                }
                R.id.nav_event ->{
                    val intent = Intent(this@MainActivity,ScrollingActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_delete ->{
                    val intent = Intent(this@MainActivity,Login::class.java)

                    val sharedPref = getSharedPreferences( "ACCESS_TOKEN", Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("ACCESS_TOKEN", "")
                        apply()
                    }

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