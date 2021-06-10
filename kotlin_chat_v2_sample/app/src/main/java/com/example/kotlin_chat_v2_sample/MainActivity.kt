package com.example.kotlin_chat_v2_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_chat_v2_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as CustomApplication

        if (app.isMissingAPIKey) {
            setContentView(R.layout.activity_missing_apikey)
        } else {

            //Loading Login view
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
    }
}
