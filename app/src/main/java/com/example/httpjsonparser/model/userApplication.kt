package com.example.httpjsonparser.model

import android.app.Application
import android.content.Context

class userApplication: Application() {

    lateinit var apiManager: ApiManager
    var clickCount = 0

    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager(this)
    }

    fun onUserClick() {
        clickCount++
    }
}