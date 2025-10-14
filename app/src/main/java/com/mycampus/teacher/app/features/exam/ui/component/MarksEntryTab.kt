package com.mycampus.teacher.app.features.exam.ui.component


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycampus.teacher.app.features.exam.domain.model.StudentMark
import com.mycampus.teacher.app.features.exam.ui.MarksEntryUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarksEntryTab(uiState: MarksEntryUiState, onUpdate: (List<StudentMark>) -> Unit) {
	
	val students: List<StudentMark> = uiState.studentsWithMarks
	val focusRequesters = remember(students.size) {
		List(students.size) { FocusRequester() }
	}
	val focusManager = LocalFocusManager.current
	
	Column(
		modifier = Modifier.padding(horizontal = 16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Icon(
				imageVector = Icons.Default.Description,
				contentDescription = null,
				modifier = Modifier.size(20.dp)
			)
			Text(
				text = "${uiState.selectedSection} - ${uiState.selectedExam}",
				fontSize = 18.sp,
				fontWeight = FontWeight.SemiBold
			)
		}
		Column {
			Text(
				text = "${uiState.selectedSubject} - ${uiState.selectedAssessment}",
				fontSize = 14.sp,
				fontWeight = FontWeight.SemiBold
			)
			Row (
				horizontalArrangement = Arrangement.spacedBy(8.dp)){
				Text(
					text = "Full Marks: 80", fontSize = 14.sp, fontWeight = FontWeight.SemiBold
				)
				Text(
					text = "Pass Marks: 33", fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
				)
			}
		}
		Spacer(modifier = Modifier.height(8.dp))
		students.forEachIndexed { index, student ->
			Card {
				Row(
					Modifier
						.fillMaxWidth()
						.padding(12.dp),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					Column {
						Text(student.name, fontWeight = FontWeight.Medium)
						Text("Roll No: ${student.rollNumber}", fontSize = 12.sp)
					}
					Row(verticalAlignment = Alignment.CenterVertically) {
						MarksTextField(
							student = student,
							index = index,
							students = students,
							onUpdate = onUpdate,
							focusRequesters = focusRequesters,
							focusManager = focusManager
						)
						
						Spacer(Modifier.width(8.dp))
						if (student.grade.isNotEmpty()) {
							AssistChip(
								onClick = {},
								label = { Text(student.grade) },
								colors = AssistChipDefaults.assistChipColors(
									containerColor = when (student.status) {
										"pass" -> Color(0xFF4CAF50)
										"fail" -> Color(0xFFF44336)
										else -> Color.Gray
									}, labelColor = Color.White
								)
							)
						}
					}
				}
			}
		}
		
	}
}
