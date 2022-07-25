package com.vignesh.attendancetracker

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.firebase.ui.auth.ui.ProgressView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vignesh.attendancetracker.fragments.DashboardFragment
import com.vignesh.attendancetracker.fragments.HomeFragment
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {
    var progressBar: ProgressBar? = null
    private var tvWait:TextView? = null
    private lateinit var myApplication: GlobalStorage
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var homeFragment: HomeFragment
    private lateinit var dashboardFragment: DashboardFragment
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        hideSystemBars()
        tvWait = findViewById(R.id.tvWait)
        progressBar = findViewById(R.id.progressBar)
        progressBar?.setVisibility(View.VISIBLE);
        tvWait?.visibility = View.VISIBLE

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        homeFragment = HomeFragment()
        dashboardFragment = DashboardFragment()
        sharedPreferences = getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
        Log.d("GlobalStorage","From Mainactivity")
        GlobalStorage.flag = false
        myApplication = GlobalStorage(sharedPreferences.getString("DEPARTMENT","hi") as String, sharedPreferences.getString("SEMESTER","hi") as String)
        val han:Handler = Handler()
        han.postDelayed(
            Runnable {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment,homeFragment)
                    commit()
                    tvWait?.visibility = View.INVISIBLE
                    progressBar?.setVisibility(View.GONE);
                }},3000
        )
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.miHome ->{
                    supportFragmentManager.beginTransaction().apply {
                        setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                        replace(R.id.flFragment,homeFragment)
                        commit()
                    }
                }
                R.id.miDashBoard ->{
                    supportFragmentManager.beginTransaction().apply {
                        setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        replace(R.id.flFragment,dashboardFragment)
                        commit()
                    }
                }
            }
            true
        }

    }
    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }



}