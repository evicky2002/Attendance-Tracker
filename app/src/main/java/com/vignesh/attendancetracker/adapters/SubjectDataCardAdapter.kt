package com.vignesh.attendancetracker.adapters

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vignesh.attendancetracker.GlobalStorage
import com.vignesh.attendancetracker.R
import com.vignesh.attendancetracker.dataModels.NewSubject


class SubjectDataCardAdapter(private val mList: ArrayList<NewSubject>, private var context: Context) : RecyclerView.Adapter<SubjectDataCardAdapter.ViewHolder>() {
    private var TAG = "SubjectDataCardAdapter"
    private var userObject = GlobalStorage.userObject
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subject_data_card, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myDialog: Dialog = Dialog(context)
        myDialog.setContentView(R.layout.history_dialog)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        holder.tvSubjectDataCardName?.text = userObject?.subjectsList?.get(position)?.subjectName
        holder.tvSubjectDataCount?.text = userObject?.subjectsList?.get(position)?.totalAbsentCount
        var totalHours = mList.get(position).subjectTotalHours.toInt()
        var totalAbsentCount = mList.get(position).totalAbsentCount.toInt()
        var remainingHours = (totalHours - totalAbsentCount)
        var percentage : Double = ( remainingHours.toDouble()/ totalHours.toDouble())* 100
        Log.d(TAG,totalHours.toString())
        Log.d(TAG,totalAbsentCount.toString())
        Log.d(TAG,remainingHours.toString())
        Log.d(TAG,percentage.toString())
        var roundedPercentage = percentage.toInt()
        holder.dataCard?.setBackgroundColor(ContextCompat.getColor(context, R.color.project_yellow))

        if(roundedPercentage < 75.0){
            holder.dataCard?.setBackgroundColor(ContextCompat.getColor(context, R.color.project_red))
        }else if(roundedPercentage < 85.0){
            holder.dataCard?.setBackgroundColor(ContextCompat.getColor(context, R.color.project_yellow))
        }else{
            holder.dataCard?.setBackgroundColor(ContextCompat.getColor(context, R.color.project_green))
        }
        holder.tvSubjectDataPercentage?.text = "$roundedPercentage%"
        val array = userObject?.subjectsList?.get(position)?.dateTracker
        if(array!!.isNotEmpty()){
            holder.dataCard?.setOnClickListener{
                var string = ""
                val tvDates = myDialog.findViewById<TextView>(R.id.tvDates)
                for(i in array){
                    val map = i
                    string+="\n\n"
                    string+=i.getValue(1.toString())
                }
                tvDates.text = string
                myDialog.show()

            }
        }


    }
    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tvSubjectDataCardName: TextView? = null
        var tvSubjectDataCount : TextView? = null
        var tvSubjectDataPercentage : TextView? = null
        var dataCard : ConstraintLayout? = null
        init{
            tvSubjectDataCardName = ItemView.findViewById(R.id.tvDates)
            tvSubjectDataCount = ItemView.findViewById(R.id.tvSubjectDataCount)
            tvSubjectDataPercentage = ItemView.findViewById(R.id.tvSubjectDataPercentage)
            dataCard = ItemView.findViewById(R.id.dataCard)
        }

    }
}