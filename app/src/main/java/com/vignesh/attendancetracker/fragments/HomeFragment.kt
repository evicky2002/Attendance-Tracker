package com.vignesh.attendancetracker.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vignesh.attendancetracker.GlobalStorage
import com.vignesh.attendancetracker.R
import com.vignesh.attendancetracker.dataModels.Semester
import com.vignesh.attendancetracker.dataModels.Subject
import com.vignesh.attendancetracker.dataModels.User

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var myApplication: GlobalStorage
    private lateinit var sharedPreferences: SharedPreferences
    private val TAG = "HomeFragment"
    private lateinit var userObject: User

    private var tvChemistry: TextView? = null
    private var tvChemCount: TextView? = null
    private var btnChemistry: Button? = null
    private lateinit var db: FirebaseFirestore
    private var user: FirebaseUser? = null
    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFirebaseAuth = FirebaseAuth.getInstance()
        user = mFirebaseAuth.currentUser
        tvChemistry = view.findViewById(R.id.tvChemistry)
        tvChemCount = view.findViewById(R.id.tvChemCount)
        btnChemistry = view.findViewById(R.id.btnChemistry)

        db = Firebase.firestore
        val handler: Handler = Handler()
        handler.postDelayed(Runnable {
},5000)
        sharedPreferences = requireActivity().getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
        GlobalStorage.flag = false
        myApplication = GlobalStorage(sharedPreferences.getString("DEPARTMENT","hi") as String, sharedPreferences.getString("SEMESTER","hi") as String)
        val han:Handler = Handler()
        han.postDelayed(
            Runnable {
                userObject = GlobalStorage.userObject
                tvChemistry?.text = userObject.subjectsList.get(0).subjectName
                tvChemCount?.text = userObject.subjectsList.get(0).totalAbsentCount

            },3000
        )
        btnChemistry?.setOnClickListener{
            var temp = userObject.subjectsList.get(0).totalAbsentCount.toInt()
            temp++
            userObject.subjectsList.get(0).totalAbsentCount = temp.toString()
            db.collection("users").document(user?.uid.toString())
                .update("subjectsList",userObject.subjectsList).addOnCompleteListener {
                    Log.d(TAG,"Updated")
                    tvChemCount?.text = userObject.subjectsList.get(0).totalAbsentCount
//                    myApplication.refreshUser()

                }
        }


    }

}