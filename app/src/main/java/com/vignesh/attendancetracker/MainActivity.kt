package com.vignesh.attendancetracker

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.vignesh.attendancetracker.dataModels.Semester
import com.vignesh.attendancetracker.dataModels.User
import com.vignesh.attendancetracker.fragments.DashboardFragment
import com.vignesh.attendancetracker.fragments.HomeFragment
import com.vignesh.attendancetracker.networkActivity.NetworkService


class MainActivity : AppCompatActivity() {
    private var TAG = "NetworkService"
    private lateinit var  sharedPreferences: SharedPreferences

    private lateinit var networkService: NetworkService

    private var semesterObject: Semester? = null
    private var userObject: User? = null

    private var progressBar: ProgressBar? = null
    private var tvWait:TextView? = null
    private var bottom:MaterialCardView? = null
    private lateinit var homeFragment: HomeFragment
    private lateinit var dashboardFragment: DashboardFragment
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
        networkService = NetworkService(this, sharedPreferences.getString("DEPARTMENT","dept") as String, sharedPreferences.getString("SEMESTER","sem") as String)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        hideSystemBars()
        tvWait = findViewById(R.id.tvWait)
        progressBar = findViewById(R.id.progressBar)
        bottom = findViewById(R.id.bottom)
        progressBar?.setVisibility(View.VISIBLE)
        tvWait?.visibility = View.VISIBLE
        bottom?.visibility = View.INVISIBLE
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottom?.visibility = View.INVISIBLE
        homeFragment = HomeFragment()
        dashboardFragment = DashboardFragment()

        if(GlobalStorage.flag){
            networkService.loadStorage(object : NetworkService.LoadStorageListener{
                override fun onResponse(response: Semester?) {
                    semesterObject = response
                    networkService.updateUser(semesterObject,object : NetworkService.UploadUserListener{
                        override fun onResponse(response: User?) {
                            userObject = response
                            GlobalStorage.userObject = userObject
                            GlobalStorage.flag = false
                            logic()
                        }
                        override fun onError(placeholder: String?) {
                            Log.d(TAG,"Error update user")
                        }

                    })
                }

                override fun onError(placeholder: String?) {
                    Log.d(TAG,"Error in load storage")
                }

            })
        }else{
            networkService.getOldUser(object : NetworkService.GetOldUserListener{
                override fun onResponse(response: User?) {
                    userObject = response
                    GlobalStorage.userObject = userObject
                    logic()
                }

                override fun onError(placeholder: String?) {
                    Log.d(TAG,"Error in get old user")
                }

            })
        }
    }


    private fun logic(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,homeFragment)
            commit()
            bottom?.visibility = View.VISIBLE
            bottom?.visibility = View.VISIBLE
            tvWait?.visibility = View.INVISIBLE
            progressBar?.setVisibility(View.GONE)
        }
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