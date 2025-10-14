package com.mycampus.teacher.app.features.exam.ui


import androidx.lifecycle.ViewModel
import com.mycampus.teacher.app.common.domain.model.AssessmentType
import com.mycampus.teacher.app.common.domain.model.ExamTemplate
import com.mycampus.teacher.app.common.domain.model.Section
import com.mycampus.teacher.app.common.domain.model.Subject
import com.mycampus.teacher.app.core.utils.SnackbarManager
import com.mycampus.teacher.app.features.exam.domain.model.StudentMark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale
import javax.inject.Inject

data class MarksEntryUiState(
	val selectedTab: Int = 0,
	val selectedSection: String = "",
	val selectedExam: String = "",
	val selectedSubject: String = "",
	val selectedAssessment: String = "",
	val selectedDate: LocalDate = LocalDate.now(),
	val sections: List<Section> = emptyList(),
	val exams: List<ExamTemplate> = emptyList(),
	val subjects: List<Subject> = emptyList(),
	val assessments: List<AssessmentType> = emptyList(),
	val studentsWithMarks: List<StudentMark> = emptyList(),
)

@HiltViewModel
class MarksEntryViewModel @Inject constructor(
	val snackbarManager: SnackbarManager
) : ViewModel() {
	
	private val _uiState = MutableStateFlow(
		MarksEntryUiState(
			sections = listOf(
				Section("1", "Three-Shapla"),
				Section("2", "Four-Golap"),
				Section("3", "Nine-Science-A"),
			),
			
			exams = listOf(
				ExamTemplate("1", "Half Yearly"),
				ExamTemplate("1", "Year Final"),
			),
			
			subjects = listOf(
				Subject("1", "Mathematics"),
				Subject("2", "Algebra"),
				Subject("3", "Geometry"),
			),
			
			assessments = listOf(
				AssessmentType("1", "Monthly Test"),
				AssessmentType("2", "Creative"),
				AssessmentType("3", "Objective"),
				AssessmentType("4", "Practical"),
			),
			studentsWithMarks = listOf(
				StudentMark("1", "Alice Johnson", "001", null, "", "pending"),
				StudentMark("2", "Bob Smith", "002", "85.6", "A", "pass"),
				StudentMark("3", "Charlie Brown", "003", "72.78", "B", "pass"),
				StudentMark("4", "Diana Ross", "004", null, "", "pending"),
				StudentMark("5", "Edward Lee", "005", "91", "A+", "pass"),
				StudentMark("6", "Fiona Green", "006", "45", "F", "fail"),
				StudentMark("7", "George Wilson", "007", null, "", "pending"),
				StudentMark("8", "Hannah White", "008", "78", "B+", "pass"),
			),
			
			
			)
	)
	val uiState: StateFlow<MarksEntryUiState> = _uiState
	
	private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
	
	fun updateTab(tabIndex: Int) {
		_uiState.value = _uiState.value.copy(selectedTab = tabIndex)
	}
	
	fun selectSection(section: String) {
		_uiState.value = _uiState.value.copy(selectedSection = section)
	}
	
	fun selectExam(exam: String) {
		_uiState.value = _uiState.value.copy(selectedExam = exam)
	}
	
	fun selectSubject(subject: String) {
		_uiState.value = _uiState.value.copy(selectedSubject = subject)
	}
	
	fun selectAssessment(assessment: String) {
		_uiState.value = _uiState.value.copy(selectedAssessment = assessment)
	}
	
	
	fun isFormValid(): Boolean =
		(_uiState.value.selectedSection.isNotEmpty() && _uiState.value.selectedExam.isNotEmpty() && _uiState.value.selectedSubject.isNotEmpty() && _uiState.value.selectedAssessment.isNotEmpty())
	
	fun updateStudentsMarks(studentsMarks: List<StudentMark>) {
		_uiState.value = _uiState.value.copy(studentsWithMarks = studentsMarks)
	}
	
}
