package com.mycampus.teacher.app.features.auth.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
	@SerializedName("login_id") val loginId: String,
	@SerializedName("school_id") val schoolId: String,
	@SerializedName("password") val password: String
)