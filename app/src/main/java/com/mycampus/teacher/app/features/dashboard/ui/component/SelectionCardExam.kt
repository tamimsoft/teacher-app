package com.mycampus.teacher.app.features.dashboard.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.common.component.DropdownField
import com.mycampus.teacher.app.features.exam.ui.MarksEntryUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionCardExam(
	uiState: MarksEntryUiState,
	onSectionSelected: (String) -> Unit,
	onExamSelected: (String) -> Unit,
	onSubjectSelected: (String) -> Unit,
	onAssessmentSelected: (String) -> Unit,
) {
	Card(
		shape = RoundedCornerShape(12.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp)
	) {
		Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
			Text(
				text = "Select Exam Details",
				style = MaterialTheme.typography.titleMedium,
				fontWeight = FontWeight.SemiBold
			)
			
			DropdownField(
				label = "Section",
				value = uiState.selectedSection,
				items = uiState.sections.map { it -> it.name },
				onItemSelected = onSectionSelected,
			)
			DropdownField(
				label = "Exam",
				value = uiState.selectedExam,
				items = uiState.exams.map { it -> it.name },
				onItemSelected = onExamSelected,
			)
			
			DropdownField(
				label = "Subject",
				value = uiState.selectedSubject,
				items = uiState.subjects.map { it -> it.name },
				onItemSelected = onSubjectSelected
			)
			DropdownField(
				label = "Assessment",
				value = uiState.selectedAssessment,
				items = uiState.assessments.map { it -> it.name },
				onItemSelected = onAssessmentSelected,
			)
		}
	}
}
