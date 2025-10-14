package com.mycampus.teacher.app.features.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycampus.teacher.app.core.network.ApiResult
import com.mycampus.teacher.app.core.network.Pagination
import com.mycampus.teacher.app.core.network.onSuccess
import com.mycampus.teacher.app.core.storage.SecureStore
import com.mycampus.teacher.app.core.utils.SnackbarManager
import com.mycampus.teacher.app.features.auth.data.local.SchoolEntity
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginRequest
import com.mycampus.teacher.app.features.auth.data.remote.model.LoginResponse
import com.mycampus.teacher.app.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class LoginUiState(
	val selectedSchool: SchoolEntity = SchoolEntity.EMPTY,
	val schools: List<SchoolEntity> = emptyList(),
	val loginId: String = "",
	val password: String = "",
	val navigateToDashboard: Boolean = false,
	val isLoading: Boolean = false
) {
	val isFormValid: Boolean
		get() = selectedSchool.isValid && loginId.isNotBlank() && password.isNotBlank()
}


@HiltViewModel
class LoginViewModel @Inject constructor(
	val snackbarManager: SnackbarManager,
	private val repository: AuthRepository,
	private val secureStore: SecureStore,
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(LoginUiState())
	
	val uiState: StateFlow<LoginUiState> = _uiState
	private var _currentPage = 1
	private val _perPage = 10
	private var _totalPage = 0
	private val _loginResponseState = MutableStateFlow<ApiResult<LoginResponse>>(ApiResult.Loading)
	val loginResponseState: StateFlow<ApiResult<LoginResponse>> = _loginResponseState
	
	init {
		fetchSchools()
	}
	
	fun onScrollUp() {
		val nextPage = _currentPage + 1
		if (nextPage != _totalPage) fetchSchools(Pagination(page = nextPage, perPage = _perPage))
	}
	
	fun fetchSchools(pagination: Pagination? = null) {
		viewModelScope.launch {
			val result = withContext(Dispatchers.IO) {
				repository.getSchools(pagination)
			}
			
			result.onSuccess { list ->
				_uiState.update { state ->
					val updatedSchools = if (pagination != null && pagination.page > 1) {
						state.schools + list // append next page
					} else {
						list // first page
					}
					if (_totalPage == 0) {
						_totalPage = result.pagination?.totalPages ?: 0
					}
					_currentPage = pagination?.page ?: 1
					state.copy(schools = updatedSchools)
				}
			}
			
			if (result is ApiResult.Error) {
				snackbarManager.showMessage("Error: ${result.message}")
			}
		}
	}
	
	
	fun login() {
		if (!_uiState.value.isFormValid) return
		
		_uiState.update { it.copy(isLoading = true) }
		val loginRequest = LoginRequest(
			schoolId = _uiState.value.selectedSchool.id.toString(),
			loginId = _uiState.value.loginId,
			password = _uiState.value.password,
		)
		
		viewModelScope.launch {
			val result = withContext(Dispatchers.IO) {
				repository.login(loginRequest)
			}
			
			_loginResponseState.value = result
			
			
			if (result is ApiResult.Success) {
				if (result.accessToken.isNullOrEmpty()) {
					snackbarManager.showMessage("Login failed: ${result.message}")
				} else {
					secureStore.saveAccessToken(result.accessToken)
					
					_uiState.update {
						it.copy(isLoading = false, navigateToDashboard = true)
					}
				}
			}
			
			if (result is ApiResult.Error) {
				snackbarManager.showMessage("Login failed: ${result.message}")
			}
		}
	}
	
	fun resetNavigationFlag() = _uiState.update { it.copy(navigateToDashboard = false) }
	
	fun onSchoolSelected(school: SchoolEntity) = _uiState.update { it.copy(selectedSchool = school) }
	fun updateLoginId(loginId: String) = _uiState.update { it.copy(loginId = loginId) }
	fun updatePassword(password: String) = _uiState.update { it.copy(password = password) }
}

