package com.mycampus.teacher.app.core.network

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

private val SUCCESS_CODES = setOf(200, 201, 202, 204)
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<ApiResponse<T>>): ApiResult<T> {
	return try {
		val response = apiCall()
		
		if (!response.isSuccessful) {
			val errorBody = response.errorBody()?.string() ?: "Unknown error"
			return ApiResult.Error(errorBody, response.code())
		}
		val body = response.body() ?: return ApiResult.Error("Response body is null", response.code())
		
		// Check status and content
		if (body.status in SUCCESS_CODES) {
			if (body.content != null) {
				ApiResult.Success(
					message = body.message,
					data = body.content,
					pagination = body.pagination,
					accessToken = body.accessToken,
					refreshToken = body.refreshToken
				)
			} else {
				// 204 No Content or similar
				ApiResult.Success(
					data = Unit as T, // placeholder for empty content
					message = body.message, accessToken = body.accessToken, refreshToken = body.refreshToken
				)
			}
		} else {
			ApiResult.Error(body.message, response.code())
		}
		
	} catch (e: IOException) {
		ApiResult.Error("Network error: ${e.localizedMessage}", throwable = e)
	} catch (e: HttpException) {
		ApiResult.Error("HTTP error: ${e.localizedMessage}", e.code(), e)
	} catch (e: Exception) {
		ApiResult.Error("Unknown error: ${e.localizedMessage}", throwable = e)
	}
}

// Extension helpers
fun <T, R> ApiResult<T>.fold(transform: (T) -> R): ApiResult<R> = when (this) {
	is ApiResult.Success -> ApiResult.Success(
		message = message,
		data = transform(data),
		pagination = pagination,
		accessToken = accessToken,
		refreshToken = refreshToken
	)
	
	is ApiResult.Error -> ApiResult.Error(message, code, throwable)
	ApiResult.Loading -> ApiResult.Loading
}

fun <T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
	if (this is ApiResult.Success) action(data)
	return this
}

fun <T> ApiResult<T>.onError(action: (String, Int?) -> Unit): ApiResult<T> {
	if (this is ApiResult.Error) action(message, code)
	return this
}

fun <T> ApiResult<T>.onLoading(action: () -> Unit): ApiResult<T> {
	if (this is ApiResult.Loading) action()
	return this
}
