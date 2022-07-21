package com.vignesh.attendancetracker.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.vignesh.attendancetracker.LoginActivity
import com.vignesh.attendancetracker.R

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private lateinit var mFirebaseAuth: FirebaseAuth
    private var tvSignOut: TextView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mFirebaseAuth = FirebaseAuth.getInstance()
        tvSignOut = view.findViewById(R.id.tvSignout)
        tvSignOut?.setOnClickListener{
            mFirebaseAuth.signOut()
            val logout = Intent(view.context,LoginActivity::class.java)
            logout.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logout)
        }
    }
}
