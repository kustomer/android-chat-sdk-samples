package com.example.kustomerloginanddescribe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kustomerloginanddescribe.databinding.ActivityMainBinding
import com.example.kustomerloginanddescribe.ui.homepage.HomepageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomepageFragment())
                .commitNow()
        }
    }
}