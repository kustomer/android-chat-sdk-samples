package com.example.kotlin_chat_v2_sample

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.kustomer.ui.Kustomer

class CustomApplication : Application() {

    var isMissingAPIKey: Boolean = false

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.API_KEY.isBlank()) {
            isMissingAPIKey = true
        } else {
            try {
                // Always initialize the Kustomer SDK in your Application class
                Kustomer.init(application = this, apiKey = BuildConfig.API_KEY) {
                    Log.d("KustomerApplication", "Kustomer initialized: ${it.dataOrNull}")
                }
            } catch (e: AssertionError) {

                //Handling exceptions related with invalid API keys
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
                isMissingAPIKey = true
            }
        }
    }
}
