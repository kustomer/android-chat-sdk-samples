package com.example.kustomerloginanddescribe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kustomerloginanddescribe.databinding.ActivityMainBinding
import com.example.kustomerloginanddescribe.ui.homepage.LoginFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // val navHostFragment =
        //     supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        // val navController = navHostFragment.navController
        //
        // setupActionBarWithNavController(navController)
    }

    // override fun onSupportNavigateUp(): Boolean {
    //     val navHostFragment =
    //         supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
    //     val navController =
    //         navHostFragment.navController
    //     return navController.navigateUp() || super.onSupportNavigateUp()
    // }
}