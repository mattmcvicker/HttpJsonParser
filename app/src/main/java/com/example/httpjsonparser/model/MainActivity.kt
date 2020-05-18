package com.example.httpjsonparser.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.httpjsonparser.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var apiManager: ApiManager
    lateinit var holdOldValue: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiManager = (application as userApplication).apiManager

        btnFetchJSON.setOnClickListener {
            fetchWithVolley()
        }

        changeUserInfo()
    }

    private fun changeUserInfo() {
        val userApp: userApplication = applicationContext as userApplication
        clicks.setOnClickListener {
            userApp.onUserClick()
            var clickCount = userApp.clickCount
            userClicks.text = clickCount.toString()
        }

    }

    private fun fetchWithVolley() {
        apiManager.getUser ({ User ->
            Log.i("hello", User.firstName)
            userName.text = User.username
            Picasso.get().load(User.profilePicURL).into(profilePic);
        },
            {
                Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show()
            })
    }

}
