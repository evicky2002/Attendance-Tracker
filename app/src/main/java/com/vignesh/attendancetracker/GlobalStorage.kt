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
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.logging.Handler

class GlobalStorage : Application() {
    companion object{
        var flag = false
        var userObject: User? = null
    }
}