package com.mycampus.teacher.app.features.attendance.domain.model

import com.mycampus.teacher.app.features.attendance.ui.AttendanceStatus

data class Student(
	val id: String,
	val name: String,
	val rollNumber: String,
	val status: AttendanceStatus = AttendanceStatus.ABSENT
)