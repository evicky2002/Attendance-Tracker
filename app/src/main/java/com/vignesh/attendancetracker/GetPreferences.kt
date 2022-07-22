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

class GetPreferences : AppCompatActivity() {
    private var TAG = "GetPreferences"
    private lateinit var myApplication: GlobalStorage
    private lateinit var sharedPreferences: SharedPreferences
    //    private var etDepartment: EditText? = null
//    private var etSemester: EditText? = null
//    private var btnSubmit: Button? = null
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

//        etDepartment = findViewById(R.id.etDepartment)
//        etSemester = findViewById(R.id.etSemester)
//        btnSubmit = findViewById(R.id.btnSubmit)
//        btnSubmit?.setOnClickListener{
//            if(etDepartment.toString().isNotEmpty() && etSemester.toString().isNotEmpty()){
//                editor.putString("DEPARTMENT",etDepartment?.text.toString().trim())
//                editor.putString("SEMESTER",etSemester?.text.toString().trim())
//                editor.commit()
//                Log.d(TAG,sharedPreferences.getString("SEMESTER","hi") as String)
//
//                startActivity(Intent(this,MainActivity::class.java))
//                finish()
//            }else{
//                Toast.makeText(this,"Enter everything",Toast.LENGTH_SHORT).show()
//            }
//        }
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
        GlobalStorage.flag = true
        myApplication = GlobalStorage(sharedPreferences.getString("DEPARTMENT","hi") as String, sharedPreferences.getString("SEMESTER","hi") as String)
        super.finish()
    }

}