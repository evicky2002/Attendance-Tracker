package com.vignesh.attendancetracker.adapters

import android.content.Context
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vignesh.attendancetracker.GlobalStorage
import com.vignesh.attendancetracker.R
import com.vignesh.attendancetracker.dataModels.NewSubject
import com.vignesh.attendancetracker.dataModels.User
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SubjectUpdateCardAdapter(private val mList: ArrayList<NewSubject>, private var context: Context) : RecyclerView.Adapter<SubjectUpdateCardAdapter.ViewHolder>() {
    private var TAG = "SubjectUpdateCardAdapter"
    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var user: FirebaseUser? = mFirebaseAuth.currentUser
    private var userObject: User = GlobalStorage.userObject
    private var db: FirebaseFirestore = Firebase.firestore
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_data_update_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvSubjectCardName?.text = mList.get(position).subjectName
        holder.tvSubjectCardCount?.text = "0"
        var temp = 0

        holder.ivPlus?.setOnClickListener{
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            temp= holder.tvSubjectCardCount?.text.toString().toInt()
            temp++
            holder.tvSubjectCardCount?.text = temp.toString()
        }
        holder.ivMinus?.setOnClickListener{
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            temp= holder.tvSubjectCardCount?.text.toString().toInt()
            temp--
            holder.tvSubjectCardCount?.text = temp.toString()
        }
        holder.btnUpdate?.setOnClickListener{
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            var count = userObject.subjectsList.get(position).totalAbsentCount.toInt()
            count+=temp
            if(temp>0){
                val map = mapOf<String,String>(temp.toString() to getCurrentDate())
                userObject.subjectsList.get(position).dateTracker?.add(map)
            }
            userObject.subjectsList.get(position).totalAbsentCount = count.toString()
            holder.tvSubjectCardCount?.text = "0"
            db.collection("users").document(user?.uid.toString())
                .update("subjectsList",userObject.subjectsList).addOnCompleteListener {
                    Toast.makeText(context,"Updated",Toast.LENGTH_SHORT).show()
                    Log.d(TAG,userObject.toString())

                }
        }

    }
    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        return sdf.format(Date())
    }
    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tvSubjectCardName : TextView? = null
        var tvSubjectCardCount : TextView? = null
        var btnUpdate : Button? = null
        var ivMinus : ImageView? = null
        var ivPlus : ImageView? = null
        init{
            tvSubjectCardName = ItemView.findViewById(R.id.tvSubjectCardName)
            tvSubjectCardCount = ItemView.findViewById(R.id.tvSubjectCardCount)
            ivMinus = ItemView.findViewById(R.id.ivMinus)
            ivPlus = ItemView.findViewById(R.id.ivPlus)
            btnUpdate = ItemView.findViewById(R.id.btnUpdate)
        }

    }
}