package com.vignesh.attendancetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vignesh.attendancetracker.fragments.DashboardFragment
import com.vignesh.attendancetracker.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var homeFragment: HomeFragment
    private lateinit var dashboardFragment: DashboardFragment
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        homeFragment = HomeFragment()
        dashboardFragment = DashboardFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,homeFragment)
            commit()
        }
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.miHome ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flFragment,homeFragment)
                        commit()
                    }
                }
                R.id.miDashBoard ->{
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flFragment,dashboardFragment)
                        commit()
                    }
                }
            }
            true
        }

    }


}