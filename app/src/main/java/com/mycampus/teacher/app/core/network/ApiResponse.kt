package com.mycampus.teacher.app.core.network

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
	val status: Int,
	val message: String,
	val content: T?,
	val pagination: Pagination?,
	@SerializedName("access_token") val accessToken: String?,
	@SerializedName("refresh_token") val refreshToken: String?,
)

// Pagination info
data class Pagination(
	val page: Int,
	@SerializedName("per_page") val perPage: Int,
	@SerializedName("total_item") val totalItem: String? = null,
	@SerializedName("total_pages") val totalPages: Int? = null,
)