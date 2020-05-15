package com.example.httpjsonparser.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class ApiManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun getUser(onUserReady: (User) -> Unit, onError: (() -> Unit)? = null) {
        val userURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/user_info.json"

        val request = StringRequest(
            Request.Method.GET, userURL,
            { response ->
                //Success
                Log.i("Hello", response)

                val gson = Gson()
                val data = gson.fromJson(response, User::class.java)
                Log.i("Tag", "User is ${data.username}")

                onUserReady(data)
            },
            {
                onError?.invoke()
            }
        )

        queue.add(request)
    }
}