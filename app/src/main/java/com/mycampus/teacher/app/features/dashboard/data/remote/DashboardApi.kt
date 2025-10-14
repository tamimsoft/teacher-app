package com.mycampus.teacher.app.features.dashboard.data.remote

import com.mycampus.teacher.app.core.network.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashboardApi {
	@GET("dashboard_data")
	suspend fun getDashboardData(): Response<ApiResponse<DashboardDto>>
}
