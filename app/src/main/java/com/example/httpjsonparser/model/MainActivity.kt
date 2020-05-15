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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiManager = (application as userApplication).apiManager

        btnFetchJSON.setOnClickListener {
            fetchWithVolley()
            fetchDataWithGson()
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
        },
            {
                Toast.makeText(this, "Error",Toast.LENGTH_SHORT).show()
            })
    }

    private fun fetchDataWithGson() {
        val gson = Gson()
        val user: User = gson.fromJson(userNameOverviewJSONString, User::class.java)
        val first = user.firstName
        userName.text = user.username
        Picasso.get().load(user.profilePicURL).into(profilePic);

    }


    val userNameOverviewJSONString = """
        {
          "username": "whomustnotbenamed",
          "firstName": "Tom",
          "lastName": "Riddle",
          "hasNose": false,
          "platform": 9.75,
          "profilePicURL": "https://picsum.photos/seed/voldemort/256"
        }
    """.trimIndent()
}
