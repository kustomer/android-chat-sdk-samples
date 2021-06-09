package com.example.kustomerloginanddescribe

import android.app.Application
import android.util.Log
import com.kustomer.ui.Kustomer

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize the Kustomer SDK in your Application class
        Kustomer.init(application = this, apiKey = BuildConfig.API_KEY) {
            Log.d("KustomerApplication", "Kustomer initialized: ${it.dataOrNull}")
        }
    }
}