package com.vignesh.attendancetracker.dataModels
data class NewSubject(
    var subjectCode : String,
    var subjectCredits : String,
    var subjectName : String,
    var subjectTotalHours : String,
    var totalAbsentCount: String,
    var dateTracker: ArrayList<Map<String,String>>?
)
