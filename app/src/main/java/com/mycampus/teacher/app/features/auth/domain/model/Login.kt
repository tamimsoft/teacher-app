package com.mycampus.teacher.app.features.auth.domain.model

data class Login(
	val schoolId: String, val year: String, val loginId: String, val password: String
)
