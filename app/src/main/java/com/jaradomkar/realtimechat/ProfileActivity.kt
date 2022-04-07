package com.jaradomkar.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.jaradomkar.realtimechat.model.userInfoData
import com.jaradomkar.realtimechat.repository.Repository
import okhttp3.MediaType.Companion.toMediaType

class ProfileActivity : AppCompatActivity() {

    private lateinit var backArrow:ImageView
    private lateinit var changePassword:Button
    private lateinit var viewModel:MainViewModel
    private lateinit var editName:EditText
    private lateinit var editEmail:EditText
    private lateinit var editPhone:EditText
    private lateinit var editBranch:EditText
    private lateinit var editYear:EditText
    private lateinit var editPresenti:EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository);
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java);

        val userData = userInfoData(intent.getStringExtra("email").toString())
        viewModel.pushEmailForUserInfo(userData)



        backArrow=findViewById(R.id.back_arrow)
        changePassword=findViewById(R.id.user_change_password)
        editName=findViewById(R.id.edit_name)
        editEmail=findViewById(R.id.edit_email)
        editPhone=findViewById(R.id.edit_phone)
        editBranch=findViewById(R.id.edit_branch)
        editYear=findViewById(R.id.edit_year)
        editPresenti=findViewById(R.id.edit_presenti)


        viewModel.userDataResponse.observe(this) { response ->
            if (response.isSuccessful) {

                editName.setText(response.body()?.userInfo?.name.toString())
                editEmail.setText(response.body()?.userInfo?.email.toString())
                editPhone.setText(response.body()?.userInfo?.phone.toString())
                editBranch.setText(response.body()?.userInfo?.branch.toString())
                editYear.setText(response.body()?.userInfo?.year.toString())

                Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }
        }


//
//       editName.extractText(intent.getStringExtra("name").toString())
//       editEmail.extractText(intent.getStringExtra("email").toString())
//       editPhone.extractText(intent.getStringExtra("phone").toString())
//       editBranch.extractText(intent.getStringExtra("branch").toString())
//       editYear.extractText(intent.getStringExtra("year").toString())

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

private fun EditText.extractText(toString: String) {

}
