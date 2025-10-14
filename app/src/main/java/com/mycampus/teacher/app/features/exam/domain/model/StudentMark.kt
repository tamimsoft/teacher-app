package com.mycampus.teacher.app.features.exam.domain.model

data class StudentMark(
	val id: String,
	val name: String,
	val rollNumber: String,
	var marks: String?,
	var grade: String,
	var status: String // "pass", "fail", "pending"
)