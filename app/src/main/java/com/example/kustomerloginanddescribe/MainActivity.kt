package com.example.kustomerloginanddescribe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kustomerloginanddescribe.databinding.MainActivityBinding
import com.example.kustomerloginanddescribe.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commitNow()
        }
    }
}