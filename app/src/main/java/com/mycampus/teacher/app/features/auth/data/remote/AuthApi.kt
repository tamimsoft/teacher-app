package com.mycampus.teacher.app.features.auth.data.remote

import com.mycampus.teacher.app.core.network.ApiResponse
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginRequest
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginResponse
import com.mycampus.teacher.app.features.auth.data.remote.model.School
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
	@GET("schools")
	suspend fun getSchools(
		@Query("page") page: Int?, @Query("per_page") perPage: Int?
	): Response<ApiResponse<List<School>>>
	
	@GET("session-years")
	suspend fun getSessionYears(): Response<ApiResponse<List<String>>>
	
	@POST("login")
	suspend fun login(@Body loginRequest: LoginRequest): Response<ApiResponse<LoginResponse>>
	
}
