package com.mycampus.teacher.app.features.profile.domain.model

data class Teacher(
	val name: String,
	val email: String,
	val phone: String,
	val address: String,
	val subjects: List<String>,
	val experience: String,
	val education: String,
	val joinDate: String
)