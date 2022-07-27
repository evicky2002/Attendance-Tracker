package com.vignesh.attendancetracker

import android.app.Application
import com.vignesh.attendancetracker.dataModels.User

class GlobalStorage : Application() {
    companion object{
        var flag = false
        var userObject: User? = null
    }
}