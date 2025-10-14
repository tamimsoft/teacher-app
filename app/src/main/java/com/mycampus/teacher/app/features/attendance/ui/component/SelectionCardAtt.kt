package com.mycampus.teacher.app.features.attendance.ui.component

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
import com.mycampus.teacher.app.features.attendance.ui.AttendanceUiState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionCardAtt(
	uiState: AttendanceUiState,
	onSectionSelected: (String) -> Unit,
	onSubjectSelected: (String) -> Unit,
	onDateChange: (LocalDate) -> Unit
) {
	Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
		Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
			Text(
				text = if (uiState.selectedTab == 0) "Select Section & Date" else "Select Section, Subject & Date",
				style = MaterialTheme.typography.titleMedium,
				fontWeight = FontWeight.SemiBold
			)
			
			DropdownField(
				label = "Section",
				value = uiState.selectedSection,
				items = uiState.sections,
				onItemSelected = onSectionSelected,
			)
			
			if (uiState.selectedTab == 1) {
				DropdownField(
					label = "Subject",
					value = uiState.selectedSubject,
					items = uiState.subjects,
					onItemSelected = onSubjectSelected
				)
			}
			DatePickerButton(
				date = uiState.selectedDate, onDateChange = onDateChange
			)
		}
	}
}
