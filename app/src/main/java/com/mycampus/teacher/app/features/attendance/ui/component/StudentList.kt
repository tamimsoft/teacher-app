package com.mycampus.teacher.app.features.attendance.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.attendance.domain.model.Student
import com.mycampus.teacher.app.features.attendance.ui.AttendanceStatus

@Composable
fun StudentList(
	students: List<Student>, onStatusChange: (String, AttendanceStatus) -> Unit
) {
	Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
		Text(
			"Student List", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold
		)
		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			students.forEach { student ->
				StudentAttendanceCard(
					student = student, onStatusChange = { status -> onStatusChange(student.id, status) })
			}
		}
	}
}

