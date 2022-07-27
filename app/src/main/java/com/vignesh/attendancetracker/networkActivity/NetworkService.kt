package com.vignesh.attendancetracker.networkActivity

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

open class NetworkService(context: Context, department: String, semester: String) {
    private var department = department
    private var semester = semester
    private var TAG = "NetworkService"
    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var db: FirebaseFirestore = Firebase.firestore

    interface LoadStorageListener {
        fun onResponse(response: Semester?)
        fun onError(placeholder: String?)
    }

    interface UploadUserListener {
        fun onResponse(response: User?)
        fun onError(placeholder: String?)
    }
    interface GetOldUserListener {
        fun onResponse(response: User?)
        fun onError(placeholder: String?)
    }

    open fun loadStorage(loadStorageListener: LoadStorageListener){
        Log.d(TAG,"Load Storage : Started")
        db.collection("departments").document(department).collection("semesters")
            .document(semester).collection("subjects")
            .get()
            .addOnSuccessListener { result ->
                Log.d(TAG,"Load Storage : Result Obtained")
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
                Log.d(TAG,"Load Storage : Your algorithm")
                Log.d(TAG,"Load Storage : Finished")
                loadStorageListener.onResponse(Semester(list))
            }
            .addOnFailureListener { exception ->
                loadStorageListener.onError("No semester object")
                Log.d(TAG,"Failure")
            }
    }

    open fun updateUser(semester:Semester?, updateUserListener: UploadUserListener){
        Log.d(TAG,"Upload user : Started")
        val user: FirebaseUser? = mFirebaseAuth.currentUser
        var list: ArrayList<NewSubject> = ArrayList()
        for(i in semester!!.subjectsList){
            list.add(
                NewSubject(
                subjectCode = i.subjectCode,
                subjectCredits = i.subjectCredits,
                subjectName = i.subjectName,
                subjectTotalHours = i.subjectTotalHours,
                totalAbsentCount = "0",
                dateTracker = ArrayList()
            )
            )
        }
        db.collection("users").document(user?.uid.toString()).set(
            User(list)
        )
            .addOnSuccessListener { result ->
                Log.d(TAG,"Upload user : Finished")
                updateUserListener.onResponse(User(list))
            }
            .addOnFailureListener { exception ->
                updateUserListener.onError("User object not updated")
                Log.d(TAG,"Failure")
            }
    }

    open fun getOldUser(getOldUserListener: GetOldUserListener){
        Log.d(TAG,"From Mainactivity")
        Log.d(TAG,"Get old user : Started")
        val user: FirebaseUser? = mFirebaseAuth.currentUser
        var list: ArrayList<NewSubject> = ArrayList()
        db.collection("users").document(user?.uid.toString()).get()
            .addOnSuccessListener { result ->
                var array: ArrayList<NewSubject> = result.get("subjectsList") as ArrayList<NewSubject> /* = java.util.ArrayList<com.vignesh.attendancetracker.dataModels.NewSubject> */
                for(i in 0..array.size-1){
                    val map: Map<String,String> = array[i] as Map<String, String>
                    val date = map.get("dateTracker") as ArrayList<Map<String,String>>
                    list.add(
                        NewSubject(map.get("subjectCode").toString(),map.get("subjectCredits").toString()
                            ,map.get("subjectName").toString(),map.get("subjectTotalHours").toString(),map.get("totalAbsentCount").toString(),date)
                    )
                }
                Log.d(TAG,"Get old user : finished")
                getOldUserListener.onResponse(User(list))
            }
            .addOnFailureListener { exception ->
                getOldUserListener.onError("No user retreived")
                Log.d(TAG,"Failure")
            }
    }


}