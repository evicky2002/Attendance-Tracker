package com.vignesh.attendancetracker.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vignesh.attendancetracker.R
import com.vignesh.attendancetracker.dataModels.Semester
import com.vignesh.attendancetracker.dataModels.Subject

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val TAG = "HomeFragment"
    private var tvHome: TextView? = null
    private lateinit var db: FirebaseFirestore
    private lateinit var mFirebaseAuth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = Firebase.firestore
        tvHome = view.findViewById(R.id.tvHome)
        tvHome?.setOnClickListener{
            db.collection("departments").document("CSE").collection("semesters")
                .document("semesterOne").collection("subjects").document("subjectOne")
                .get()
                .addOnSuccessListener { result ->
                    Log.d(TAG,"Success : "+result.get("subjectName"))
                    val subject = Subject(
                        result.get("subjectName") as String,
                        result.get("subjectCredits") as String,
                        result.get("subjectTotalHours") as String,
                        result.get("subjectCode") as String
                    )
                    tvHome?.text = subject.toString()
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG,"Failure")
                }
        }
    }

}