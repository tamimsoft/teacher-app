package com.mycampus.teacher.app.features.attendance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.attendance.ui.AttendanceUiState

@Composable
fun AttendanceSummary(uiState: AttendanceUiState) {
	Card(shape = RoundedCornerShape(12.dp),
		modifier = Modifier.fillMaxWidth().padding(horizontal =16.dp)) {
		Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
			Row(
				Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically
			) {
				Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
					Box(
						modifier = Modifier
							.size(48.dp)
							.background(
								brush = Brush.linearGradient(
									listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.tertiary)
								),
								shape = CircleShape
							),
						contentAlignment = Alignment.Center
					) {
						Icon(Icons.Default.People, contentDescription = null, tint = Color.White)
					}
					Column {
						Text(
							text = if (uiState.selectedSubject.isNotEmpty())
								"${uiState.selectedSubject} - ${uiState.selectedSection}"
							else uiState.selectedSection,
							style = MaterialTheme.typography.titleMedium,
							fontWeight = FontWeight.SemiBold
						)
						Text(
							text = "Total: ${uiState.students.size} students\n(${uiState.attendancePercentage}% attendance)",
							style = MaterialTheme.typography.bodySmall,
							color = MaterialTheme.colorScheme.onSurfaceVariant
						)
					}
				}
				AssistChip(
					onClick = {},
					label = { Text("${uiState.attendancePercentage}%") },
					colors = AssistChipDefaults.assistChipColors(
						containerColor = if (uiState.attendancePercentage >= 80)
							MaterialTheme.colorScheme.tertiary
						else MaterialTheme.colorScheme.error,
						labelColor = Color.White
					)
				)
			}
			
			Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
				StatColumn("Present", uiState.presentCount, MaterialTheme.colorScheme.tertiary)
				StatColumn("Late", uiState.lateCount, Color(0xFFF59E0B))
				StatColumn("Leave", uiState.leaveCount, Color(0xFF3B82F6))
				StatColumn("Absent", uiState.absentCount, MaterialTheme.colorScheme.error)
			}
		}
	}
}
