package com.vignesh.attendancetracker

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vignesh.attendancetracker.dataModels.NewSubject
import com.vignesh.attendancetracker.dataModels.Semester
import com.vignesh.attendancetracker.dataModels.Subject
import com.vignesh.attendancetracker.dataModels.User
import java.util.logging.Handler

class GlobalStorage constructor(a: String, b: String): Application() {

    private var TAG = "GlobalStorage"
    var currentDepartment:String
    var currentSemester:String
    companion object{
        var flag = false
        lateinit var userObject: User
    }
    private lateinit var semester: Semester
    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var db: FirebaseFirestore = Firebase.firestore

    init {
        val han: android.os.Handler = android.os.Handler()
        this.currentDepartment = a
        this.currentSemester = b
        han.postDelayed(Runnable {
            loadStorage()
        },100)
        if(flag){
            Log.d(TAG,"Flag check")
            han.postDelayed(Runnable {
                uploadUser()
            },2000)
        }else{
            han.postDelayed(Runnable {
                getOldUser()
            },800)
        }

    }

    fun loadStorage(){
        Log.d(TAG,"Flag check : Load")

        db.collection("departments").document(currentDepartment).collection("semesters")
            .document(currentSemester).collection("subjects")
            .get()
            .addOnSuccessListener { result ->
                var list: ArrayList<Subject> = ArrayList()
                for(i in result){
                    list.add(
                        Subject(
                            i.get("subjectCode") as String,
                            i.get("subjectCredits") as String,
                            i.get("subjectName") as String,
                            i.get("subjectTotalHours") as String,
                        )
                    )
                }
                semester = Semester(list)
                Log.d(TAG,"Semester : "+semester.toString())
            }
            .addOnFailureListener { exception ->
                Log.d(TAG,"Failure")
            }
    }

    fun uploadUser(){
        val user: FirebaseUser? = mFirebaseAuth.currentUser
        var list: ArrayList<NewSubject> = ArrayList()
        for(i in semester.subjectsList){
            list.add(NewSubject(
                subjectCode = i.subjectCode,
                subjectCredits = i.subjectCredits,
                subjectName = i.subjectName,
                subjectTotalHours = i.subjectTotalHours,
                totalAbsentCount = "0"
            ))
        }
        userObject = User(list)
        db.collection("users").document(user?.uid.toString()).set(
            userObject
        )
            .addOnSuccessListener { result ->
                Log.d(TAG,"Uploaded new user : finished")

            }
            .addOnFailureListener { exception ->
                Log.d(TAG,"Failure")
            }
    }
    fun getOldUser(){
        val user: FirebaseUser? = mFirebaseAuth.currentUser
        var list: ArrayList<NewSubject> = ArrayList()
        db.collection("users").document(user?.uid.toString()).get()
            .addOnSuccessListener { result ->
                var array: ArrayList<NewSubject> = result.get("subjectsList") as ArrayList<NewSubject> /* = java.util.ArrayList<com.vignesh.attendancetracker.dataModels.NewSubject> */
                for(i in 0..array.size-1){
                    val map: Map<String,String> = array[i] as Map<String, String>
                    list.add(NewSubject(map.get("subjectCode").toString(),map.get("subjectCredits").toString()
                        ,map.get("subjectName").toString(),map.get("subjectTotalHours").toString(),map.get("totalAbsentCount").toString()))
                }
                userObject = User(list)
                Log.d(TAG,"Got old user : finished")

            }
            .addOnFailureListener { exception ->
                Log.d(TAG,"Failure")
            }

    }
}