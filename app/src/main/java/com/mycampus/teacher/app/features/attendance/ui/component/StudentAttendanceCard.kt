package com.mycampus.teacher.app.features.attendance.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.attendance.domain.model.Student
import com.mycampus.teacher.app.features.attendance.ui.AttendanceStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentAttendanceCard(
	student: Student, onStatusChange: (AttendanceStatus) -> Unit
) {
	var expanded by remember { mutableStateOf(false) }
	
	val backgroundColor = when (student.status) {
		AttendanceStatus.PRESENT -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f)
		AttendanceStatus.LATE -> Color(0xFFF59E0B).copy(alpha = 0.1f)
		AttendanceStatus.LEAVE -> Color(0xFF3B82F6).copy(alpha = 0.1f)
		AttendanceStatus.ABSENT -> MaterialTheme.colorScheme.error.copy(alpha = 0.1f)
	}
	
	val borderColor = when (student.status) {
		AttendanceStatus.PRESENT -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f)
		AttendanceStatus.LATE -> Color(0xFFF59E0B).copy(alpha = 0.3f)
		AttendanceStatus.LEAVE -> Color(0xFF3B82F6).copy(alpha = 0.3f)
		AttendanceStatus.ABSENT -> MaterialTheme.colorScheme.error.copy(alpha = 0.3f)
	}
	
	Surface(
		modifier = Modifier.fillMaxWidth(),
		shape = RoundedCornerShape(8.dp),
		color = backgroundColor,
		border = androidx.compose.foundation.BorderStroke(1.dp, borderColor)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(12.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Column {
				Text(
					text = student.name,
					style = MaterialTheme.typography.bodyMedium,
					fontWeight = FontWeight.Medium
				)
				Text(
					text = "Roll No: ${student.rollNumber}",
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
			
			Row(
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				ExposedDropdownMenuBox(
					expanded = expanded, onExpandedChange = { expanded = !expanded }) {
					OutlinedButton(
						onClick = { expanded = true }, modifier = Modifier.menuAnchor(
							ExposedDropdownMenuAnchorType.PrimaryEditable, enabled = true
						)
					) {
						Text(student.status.name.lowercase().replaceFirstChar { it.uppercase() })
						Icon(
							Icons.Default.ArrowDropDown,
							contentDescription = "Expand",
							modifier = Modifier.size(16.dp)
						)
					}
					
					ExposedDropdownMenu(
						expanded = expanded, onDismissRequest = { expanded = false }) {
						AttendanceStatus.entries.forEach { status ->
							DropdownMenuItem(
								text = { Text(status.name.lowercase().replaceFirstChar { it.uppercase() }) },
								onClick = {
									onStatusChange(status)
									expanded = false
								})
						}
					}
				}
			}
		}
	}
}