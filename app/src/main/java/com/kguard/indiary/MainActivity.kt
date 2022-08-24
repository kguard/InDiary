package com.kguard.indiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController=navHostFragment.navController

        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.main_navi)
        setupWithNavController(bottomNavigationView,navController)

    }

    override fun onBackPressed() {
        //super.onBackPressed()
        when (navController.currentDestination)
        {

            else -> super.onBackPressed()
        }
    }
}