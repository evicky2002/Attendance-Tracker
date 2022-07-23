package com.vignesh.attendancetracker.fragments

import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.HapticFeedbackConstants
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.WindowCompat
import com.google.android.material.button.MaterialButton
import com.vignesh.attendancetracker.R

class GetDepartmentFragment : Fragment(R.layout.fragment_get_department), View.OnClickListener {
    private val getSemesterFragment = GetSemesterFragment()
    private var TAG = "GetDepartmentFragment"
    private var btnCSE: MaterialButton? = null
    private var btnECE: MaterialButton? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var btnEEE: MaterialButton? = null
    private var btnMECH: MaterialButton? = null
    private var btnNext: MaterialButton? = null
    private var isPressed: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = this.requireActivity().getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        btnCSE = view.findViewById(R.id.btnCSE)
        btnECE = view.findViewById(R.id.btnECE)
        btnEEE = view.findViewById(R.id.btnEEE)
        btnMECH = view.findViewById(R.id.btnMECH)
        btnNext = view.findViewById(R.id.btnNext)

        btnCSE?.setOnClickListener(this)
        btnECE?.setOnClickListener(this)
        btnEEE?.setOnClickListener(this)
        btnMECH?.setOnClickListener(this)
        btnNext?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        isPressed?.let { it1 -> Log.d(TAG, it1) }
        when(view?.id){
            R.id.btnCSE -> {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

                btnCSE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnCSE?.setTextColor(getResources().getColor(R.color.project_blue))

                btnECE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnECE?.setTextColor(getResources().getColor(R.color.white))
                btnEEE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnEEE?.setTextColor(getResources().getColor(R.color.white))
                btnMECH?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnMECH?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "CSE"
            }
            R.id.btnECE -> {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

                btnECE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnECE?.setTextColor(getResources().getColor(R.color.project_blue))

                btnCSE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnCSE?.setTextColor(getResources().getColor(R.color.white))
                btnEEE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnEEE?.setTextColor(getResources().getColor(R.color.white))
                btnMECH?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnMECH?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "ECE"
            }
            R.id.btnEEE -> {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

                btnEEE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnEEE?.setTextColor(getResources().getColor(R.color.project_blue))

                btnECE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnECE?.setTextColor(getResources().getColor(R.color.white))
                btnCSE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnCSE?.setTextColor(getResources().getColor(R.color.white))
                btnMECH?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnMECH?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "EEE"
            }
            R.id.btnMECH -> {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

                btnMECH?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnMECH?.setTextColor(getResources().getColor(R.color.project_blue))

                btnEEE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnEEE?.setTextColor(getResources().getColor(R.color.white))
                btnECE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnECE?.setTextColor(getResources().getColor(R.color.white))
                btnCSE?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnCSE?.setTextColor(getResources().getColor(R.color.white))

                isPressed = "MECH"
            }
            R.id.btnNext -> {
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

                if(isPressed.equals(null)){
                    Toast.makeText(activity,"Please select your Department",Toast.LENGTH_LONG).show()
                }else{
                    when(isPressed){
                        "CSE" -> {
                            editor.putString("DEPARTMENT",isPressed)
                            editor.commit()
                            requireActivity().supportFragmentManager.beginTransaction().apply {
                                replace(R.id.flFragment,getSemesterFragment)
                                commit()
                            }
                        }
                        "ECE" -> {
                            editor.putString("DEPARTMENT",isPressed)
                            editor.commit()
                            requireActivity().supportFragmentManager.beginTransaction().apply {
                                replace(R.id.flFragment,getSemesterFragment)
                                commit()
                            }
                        }
                        "EEE" -> {
                            editor.putString("DEPARTMENT",isPressed)
                            editor.commit()
                            requireActivity().supportFragmentManager.beginTransaction().apply {
                                replace(R.id.flFragment,getSemesterFragment)
                                commit()
                            }

                        }
                        "MECH" -> {
                            editor.putString("DEPARTMENT",isPressed)
                            editor.commit()
                            requireActivity().supportFragmentManager.beginTransaction().apply {
                                replace(R.id.flFragment,getSemesterFragment)
                                commit()
                            }
                        }
                    }

                }
            }
        }

    }

}