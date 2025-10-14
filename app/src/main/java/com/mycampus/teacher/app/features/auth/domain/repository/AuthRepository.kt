package com.mycampus.teacher.app.features.auth.domain.repository

import com.mycampus.teacher.app.core.network.ApiResult
import com.mycampus.teacher.app.core.network.Pagination
import com.mycampus.teacher.app.features.auth.data.local.SchoolEntity
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginRequest
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginResponse

interface AuthRepository {
	suspend fun getSchools(pagination: Pagination? = null): ApiResult<List<SchoolEntity>>
	suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse>
}