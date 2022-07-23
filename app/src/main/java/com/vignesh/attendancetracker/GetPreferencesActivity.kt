package com.vignesh.attendancetracker

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.vignesh.attendancetracker.fragments.GetDepartmentFragment
import com.vignesh.attendancetracker.fragments.GetSemesterFragment

class GetPreferencesActivity : AppCompatActivity() {
    private var TAG = "GetPreferences"
    private lateinit var myApplication: GlobalStorage
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        hideSystemBars()
        setContentView(R.layout.activity_get_preferences)

        sharedPreferences = this.getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)

        val getDepartmentFragment: GetDepartmentFragment = GetDepartmentFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, getDepartmentFragment).commit()
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

    override fun finish() {
        Log.d(TAG,"finish")
        GlobalStorage.flag = true
        myApplication = GlobalStorage(sharedPreferences.getString("DEPARTMENT","hi") as String, sharedPreferences.getString("SEMESTER","hi") as String)
        super.finish()
    }

}