package com.vignesh.attendancetracker.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.vignesh.attendancetracker.GlobalStorage
import com.vignesh.attendancetracker.LoginActivity
import com.vignesh.attendancetracker.R
import com.vignesh.attendancetracker.adapters.SubjectDataCardAdapter

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private var TAG = "DashboardFragment"
    private var rvDataCard: RecyclerView? = null
    private lateinit var mFirebaseAuth: FirebaseAuth
    private var btnSignOut: Button? = null
    private var tvTotalPercentage: TextView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userObject = GlobalStorage.userObject
        Log.d(TAG,userObject.toString())
        rvDataCard = view.findViewById(R.id.rvDataCard)
        val subjectDataCardAdapter = SubjectDataCardAdapter(userObject.subjectsList,requireContext())
        rvDataCard?.setHasFixedSize(true)
        rvDataCard?.layoutManager = GridLayoutManager(requireContext(),2)
        rvDataCard?.adapter = subjectDataCardAdapter
        Log.d(TAG,userObject.toString())
        mFirebaseAuth = FirebaseAuth.getInstance()
        btnSignOut = view.findViewById(R.id.btnSignOut)
        tvTotalPercentage = view.findViewById(R.id.tvTotalPercentage)
        btnSignOut?.setOnClickListener{
            mFirebaseAuth.signOut()
            val logout = Intent(view.context,LoginActivity::class.java)
            logout.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logout)
        }
        var percentageTotal :Double= 0.0
        var n = 0
        for(i in userObject.subjectsList){
            n++
            var total = i.subjectTotalHours.toInt()
            var absent = i.totalAbsentCount.toInt()
            var rem = total-absent
            val perc : Double= (rem.toDouble()/total.toDouble())*100
            percentageTotal+=perc
        }
        val percentage : Double = percentageTotal/n.toDouble()
        val roundedPercentage = percentage.toInt()
        tvTotalPercentage?.text = "$roundedPercentage%"

    }
}
