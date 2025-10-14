package com.mycampus.teacher.app.features.auth.data.remote

import com.mycampus.teacher.BuildConfig
import com.mycampus.teacher.app.core.network.ApiResult
import com.mycampus.teacher.app.core.network.Pagination
import com.mycampus.teacher.app.core.network.fold
import com.mycampus.teacher.app.core.network.safeApiCall
import com.mycampus.teacher.app.core.utils.logInfo
import com.mycampus.teacher.app.features.auth.data.local.SchoolEntity
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginRequest
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginResponse
import com.mycampus.teacher.app.features.auth.data.remote.model.School
import com.mycampus.teacher.app.features.auth.data.remote.model.toDomain
import com.mycampus.teacher.app.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val api: AuthApi
) : AuthRepository {
	
	override suspend fun getSchools(pagination: Pagination?): ApiResult<List<SchoolEntity>> {
		val response: ApiResult<List<School>> = safeApiCall {
			logInfo("Fetching Schools from: ${BuildConfig.BASE_URL}")
			api.getSchools(pagination?.page, pagination?.perPage)
		}
		
		// Transform API model → Domain model
		
		return response.fold { list ->
			list.map { it.toDomain() }
		}
	}
	
	override suspend fun login(loginRequest: LoginRequest): ApiResult<LoginResponse> {
		return safeApiCall {
			logInfo("Fetching User from: ${BuildConfig.BASE_URL}")
			api.login(loginRequest)
		}
	}
}