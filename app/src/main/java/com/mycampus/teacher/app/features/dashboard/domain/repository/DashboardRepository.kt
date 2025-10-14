package com.mycampus.teacher.app.features.dashboard.domain.repository

import com.mycampus.teacher.app.core.network.ApiResult
import com.mycampus.teacher.app.features.dashboard.data.remote.DashboardDto

interface DashboardRepository {
	suspend fun getDashboardData(): ApiResult<DashboardDto>
}