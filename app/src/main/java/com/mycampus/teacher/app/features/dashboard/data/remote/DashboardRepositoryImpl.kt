package com.mycampus.teacher.app.features.dashboard.data.remote

import android.util.Log
import com.mycampus.teacher.BuildConfig
import com.mycampus.teacher.app.core.network.ApiResult
import com.mycampus.teacher.app.core.network.safeApiCall
import com.mycampus.teacher.app.features.dashboard.domain.repository.DashboardRepository
import javax.inject.Inject


class DashboardRepositoryImpl @Inject constructor(
	private val api: DashboardApi
) : DashboardRepository {
	
	
	override suspend fun getDashboardData(): ApiResult<DashboardDto> {
		return safeApiCall {
			if (BuildConfig.DEBUG) {
				Log.d("DashboardRepository", "Fetching Products from: ${BuildConfig.BASE_URL}")
			}
			api.getDashboardData()
		}
	}
	
}
