package com.mycampus.teacher.app.features.attendance.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycampus.teacher.app.core.utils.SnackbarManager
import com.mycampus.teacher.app.features.attendance.domain.model.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import javax.inject.Inject

enum class AttendanceStatus {
	PRESENT, LATE, ABSENT, LEAVE
}

data class AttendanceUiState(
	val selectedTab: Int = 0,
	val selectedSection: String = "",
	val selectedSubject: String = "",
	val selectedDate: LocalDate = LocalDate.now(),
	val students: List<Student> = emptyList(),
	val sections: List<String> = listOf("Grade 9A", "Grade 9B", "Grade 10A", "Grade 10B", "Grade 11"),
	val subjects: List<String> = listOf(
		"Mathematics", "Physics", "Chemistry", "Biology", "English", "History"
	),
	val snackbarMessage: String? = null
) {
	val presentCount get() = students.count { it.status == AttendanceStatus.PRESENT }
	val lateCount get() = students.count { it.status == AttendanceStatus.LATE }
	val leaveCount get() = students.count { it.status == AttendanceStatus.LEAVE }
	val absentCount get() = students.count { it.status == AttendanceStatus.ABSENT }
	val attendancePercentage: Int
		get() = if (students.isNotEmpty()) ((presentCount + lateCount).toFloat() / students.size * 100).toInt() else 0
}

@HiltViewModel
class AttendanceViewModel @Inject constructor(
	val snackbarManager: SnackbarManager
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(
		AttendanceUiState(
			students = listOf(
				Student("1", "Alice Johnson", "001", AttendanceStatus.ABSENT),
				Student("2", "Bob Smith", "002", AttendanceStatus.ABSENT),
				Student("3", "Charlie Brown", "003", AttendanceStatus.ABSENT),
				Student("4", "Diana Ross", "004", AttendanceStatus.ABSENT),
				Student("5", "Edward Lee", "005", AttendanceStatus.ABSENT),
				Student("6", "Fiona Green", "006", AttendanceStatus.ABSENT),
				Student("7", "George Wilson", "007", AttendanceStatus.ABSENT),
				Student("8", "Hannah White", "008", AttendanceStatus.ABSENT)
			)
		)
	)
	val uiState: StateFlow<AttendanceUiState> = _uiState
	
	private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
	
	fun updateTab(tabIndex: Int) {
		_uiState.value = _uiState.value.copy(selectedTab = tabIndex)
	}
	
	fun selectClass(section: String) {
		_uiState.value = _uiState.value.copy(selectedSection = section)
	}
	
	fun selectSubject(subject: String) {
		_uiState.value = _uiState.value.copy(selectedSubject = subject)
	}
	
	fun selectDate(date: LocalDate) {
		_uiState.value = _uiState.value.copy(selectedDate = date)
	}
	
	fun updateStudentStatus(studentId: String, status: AttendanceStatus) {
		_uiState.value = _uiState.value.copy(
			students = _uiState.value.students.map {
				if (it.id == studentId) it.copy(status = status) else it
			})
	}
	
	fun markAllStatus(status: AttendanceStatus) {
		val message = "All students marked ${status.name.lowercase()}"
		_uiState.value = _uiState.value.copy(
			students = _uiState.value.students.map { it.copy(status = status) },
			snackbarMessage = "All students marked ${status.name.lowercase()}"
		
		)
		viewModelScope.launch {
			snackbarManager.showMessage(text = message)
		}
	}
	
	fun saveAttendance() {
		val state = _uiState.value
		val message =
			"Attendance saved! ${state.presentCount} present, ${state.lateCount} late, ${state.leaveCount} on leave"
		_uiState.value = state.copy(snackbarMessage = message)
		viewModelScope.launch {
			snackbarManager.showMessage(text = message, actionLabel = "Undo", onAction = {
			
			})
		}
		
	}
	
	
	fun consumeSnackbar() {
		_uiState.value = _uiState.value.copy(snackbarMessage = null)
	}
	
	fun formatDate(date: Date): String = dateFormat.format(date)
}
