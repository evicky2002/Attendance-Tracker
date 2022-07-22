package com.vignesh.attendancetracker.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.material.button.MaterialButton
import com.vignesh.attendancetracker.MainActivity
import com.vignesh.attendancetracker.R

class GetSemesterFragment : Fragment(R.layout.fragment_get_semester), View.OnClickListener {
    private var TAG = "GetSemesterFragment"
    private var btnSemOne: MaterialButton? = null
    private var btnSemTwo: MaterialButton? = null
    private var btnSemThree: MaterialButton? = null
    private var btnSemFour: MaterialButton? = null
    private var btnSemFive: MaterialButton? = null
    private var btnSemSix: MaterialButton? = null
    private var btnSemSeven: MaterialButton? = null
    private var btnSemEight: MaterialButton? = null

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var btnNext: MaterialButton? = null
    private var isPressed: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = this.requireActivity().getSharedPreferences("USER_PREFERENCE", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        btnSemOne = view.findViewById(R.id.btnSemOne)
        btnSemTwo = view.findViewById(R.id.btnSemTwo)
        btnSemThree = view.findViewById(R.id.btnSemThree)
        btnSemFour = view.findViewById(R.id.btnSemFour)
        btnSemFive = view.findViewById(R.id.btnSemFive)
        btnSemSix = view.findViewById(R.id.btnSemSix)
        btnSemSeven = view.findViewById(R.id.btnSemSeven)
        btnSemEight = view.findViewById(R.id.btnSemEight)

        btnNext = view.findViewById(R.id.btnNext)


        btnSemOne?.setOnClickListener(this)
        btnSemTwo?.setOnClickListener(this)
        btnSemThree?.setOnClickListener(this)
        btnSemFour?.setOnClickListener(this)
        btnSemFive?.setOnClickListener(this)
        btnSemSix?.setOnClickListener(this)
        btnSemSeven?.setOnClickListener(this)
        btnSemEight?.setOnClickListener(this)
        btnNext?.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnSemOne -> {
                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_green))

                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.white))
                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemThree?.setTextColor(getResources().getColor(R.color.white))
                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFour?.setTextColor(getResources().getColor(R.color.white))
                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFive?.setTextColor(getResources().getColor(R.color.white))
                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSix?.setTextColor(getResources().getColor(R.color.white))
                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.white))
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemEight?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "semesterOne"

            }
            R.id.btnSemTwo -> {
                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.project_green))

                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemThree?.setTextColor(getResources().getColor(R.color.white))
                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFour?.setTextColor(getResources().getColor(R.color.white))
                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFive?.setTextColor(getResources().getColor(R.color.white))
                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSix?.setTextColor(getResources().getColor(R.color.white))
                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.white))
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemEight?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "semesterTwo"
            }
            R.id.btnSemThree -> {
                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemThree?.setTextColor(getResources().getColor(R.color.project_green))


                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFour?.setTextColor(getResources().getColor(R.color.white))
                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFive?.setTextColor(getResources().getColor(R.color.white))
                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSix?.setTextColor(getResources().getColor(R.color.white))
                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.white))
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemEight?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "semesterThree"
            }
            R.id.btnSemFour -> {
                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemFour?.setTextColor(getResources().getColor(R.color.project_green))

                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemThree?.setTextColor(getResources().getColor(R.color.white))
                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFive?.setTextColor(getResources().getColor(R.color.white))
                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSix?.setTextColor(getResources().getColor(R.color.white))
                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.white))
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemEight?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "semesterFour"
            }
            R.id.btnSemFive -> {
                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemFive?.setTextColor(getResources().getColor(R.color.project_green))

                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFour?.setTextColor(getResources().getColor(R.color.white))
                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemThree?.setTextColor(getResources().getColor(R.color.white))
                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSix?.setTextColor(getResources().getColor(R.color.white))
                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.white))
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemEight?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "semesterFive"
            }
            R.id.btnSemSix -> {
                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemSix?.setTextColor(getResources().getColor(R.color.project_green))

                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFive?.setTextColor(getResources().getColor(R.color.white))
                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFour?.setTextColor(getResources().getColor(R.color.white))
                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemThree?.setTextColor(getResources().getColor(R.color.white))
                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.white))
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemEight?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "semesterSix"
            }
            R.id.btnSemSeven -> {
                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.project_green))

                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSix?.setTextColor(getResources().getColor(R.color.white))
                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFive?.setTextColor(getResources().getColor(R.color.white))
                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFour?.setTextColor(getResources().getColor(R.color.white))
                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemThree?.setTextColor(getResources().getColor(R.color.white))
                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemEight?.setTextColor(getResources().getColor(R.color.white))
                isPressed = "semesterSeven"
            }
            R.id.btnSemEight -> {
                btnSemEight?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
                btnSemEight?.setTextColor(getResources().getColor(R.color.project_green))

                btnSemSeven?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSeven?.setTextColor(getResources().getColor(R.color.white))
                btnSemSix?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemSix?.setTextColor(getResources().getColor(R.color.white))
                btnSemFive?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFive?.setTextColor(getResources().getColor(R.color.white))
                btnSemFour?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemFour?.setTextColor(getResources().getColor(R.color.white))
                btnSemThree?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemThree?.setTextColor(getResources().getColor(R.color.white))
                btnSemTwo?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemTwo?.setTextColor(getResources().getColor(R.color.project_white))
                btnSemOne?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.no_color))
                btnSemOne?.setTextColor(getResources().getColor(R.color.project_white))

                isPressed = "semesterEight"
            }
            R.id.btnNext -> {
                if(isPressed.equals(null)){
                    Toast.makeText(activity,"Please select your Semester", Toast.LENGTH_LONG).show()
                }else{
                    when(isPressed){
                        "semesterOne" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }"semesterTwo" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }"semesterThree" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }"semesterFour" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }"semesterFive" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }"semesterSix" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }"semesterSeven" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }"semesterEight" -> {
                            editor.putString("SEMESTER",isPressed)
                            editor.commit()
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                            requireActivity().finish()
                        }

                    }

                }
            }

        }
    }

}