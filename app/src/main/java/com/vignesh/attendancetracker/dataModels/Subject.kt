package com.vignesh.attendancetracker.dataModels

import java.io.Serializable

data class Subject(
    var subjectCode : String,
    var subjectCredits : String,
    var subjectName : String,
    var subjectTotalHours : String
): Serializable
