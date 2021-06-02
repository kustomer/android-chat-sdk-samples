package com.example.kustomerloginanddescribe

import android.app.Application
import com.kustomer.ui.Kustomer

class KustomerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Kustomer.init(application = this, apiKey = BuildConfig.API_KEY) {
        }
    }
}