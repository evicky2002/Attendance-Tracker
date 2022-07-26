package com.vignesh.attendancetracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vignesh.attendancetracker.GlobalStorage
import com.vignesh.attendancetracker.R
import com.vignesh.attendancetracker.adapters.SubjectUpdateCardAdapter
import com.vignesh.attendancetracker.dataModels.User

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val TAG = "HomeFragment"
    private var userObject: User? = null
    private var rvSubjectList: RecyclerView? = null
    private lateinit var db: FirebaseFirestore
    private var user: FirebaseUser? = null
    private lateinit var mFirebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFirebaseAuth = FirebaseAuth.getInstance()
        user = mFirebaseAuth.currentUser
        rvSubjectList = view.findViewById(R.id.rvSubjectList)
        db = Firebase.firestore
        userObject = GlobalStorage.userObject
        val subjectUpdateCardAdapter = SubjectUpdateCardAdapter(userObject!!.subjectsList, requireContext())
        rvSubjectList?.setHasFixedSize(true)
        rvSubjectList?.layoutManager = GridLayoutManager(requireContext(),2)
        rvSubjectList?.adapter = subjectUpdateCardAdapter

    }

}