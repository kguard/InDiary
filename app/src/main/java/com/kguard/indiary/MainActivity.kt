package com.kguard.indiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kguard.indiary.databinding.ActivityMainBinding
import com.kguard.indiary.databinding.FragmentMemoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController=navHostFragment.navController

        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.main_navi)
        setupWithNavController(bottomNavigationView,navController)
        navController.addOnDestinationChangedListener{_,destination, _ ->
            if(destination.id==R.id.personFragment || destination.id==R.id.memoryFragment){
                binding.mainNavi.visibility=View.VISIBLE
            }
            else{
                binding.mainNavi.visibility=View.GONE
            }
        }


        /*if(navController.currentDestination?.id == R.id.peopleFragment){
            binding.mainNavi.visibility= View.VISIBLE
        }
        else if(navController.currentDestination?.id == R.id.memoryFragment){
            binding.mainNavi.visibility= View.VISIBLE
        }
        else
        {
            binding.mainNavi.visibility= View.GONE
        }*/

    }


    override fun onBackPressed() {
        //super.onBackPressed()
        when (navController.currentDestination?.id)
        {
            R.id.personFragment -> QuitDialogFragment().show(supportFragmentManager,"Quit")
            R.id.memoryFragment -> QuitDialogFragment().show(supportFragmentManager,"Quit")
            else -> super.onBackPressed()
        }
    }
}