package com.mycampus.teacher.app.core.network

sealed class ApiResult<out T>(
	open val data: T? = null,
	open val pagination: Pagination? = null,
	open val message: String? = null,
	open val code: Int? = null,
	open val throwable: Throwable? = null,
	open val accessToken: String? = null,
	open val refreshToken: String? = null
) {
	data class Success< T>(
		override val message: String? = null,
		override val data: T,
		override val pagination: Pagination? = null,
		override val accessToken: String? = null,
		override val refreshToken: String? = null
	) : ApiResult<T>(
		message = message,
		data = data,
		pagination = pagination,
		accessToken = accessToken,
		refreshToken = refreshToken
	)
	
	data class Error(
		override val message: String,
		override val code: Int? = null,
		override val throwable: Throwable? = null
	) : ApiResult<Nothing>(message = message, code = code, throwable = throwable)
	
	object Loading : ApiResult<Nothing>()
}
