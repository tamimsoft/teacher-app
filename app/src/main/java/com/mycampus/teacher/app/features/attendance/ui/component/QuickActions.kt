package com.mycampus.teacher.app.features.attendance.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.attendance.ui.AttendanceStatus

@Composable
fun QuickActions(onMarkAll: (AttendanceStatus) -> Unit) {
	Column(
		modifier = Modifier.padding(horizontal = 16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			OutlinedButton(
				onClick = { onMarkAll(AttendanceStatus.PRESENT) }, modifier = Modifier.weight(1f)
			) {
				Text("All Present", style = MaterialTheme.typography.labelSmall)
			}
			OutlinedButton(
				onClick = { onMarkAll(AttendanceStatus.LATE) }, modifier = Modifier.weight(1f)
			) {
				Text("All Late", style = MaterialTheme.typography.labelSmall)
			}
		}
		Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			OutlinedButton(
				onClick = { onMarkAll(AttendanceStatus.LEAVE) }, modifier = Modifier.weight(1f)
			) {
				Text("All Leave", style = MaterialTheme.typography.labelSmall)
			}
			OutlinedButton(
				onClick = { onMarkAll(AttendanceStatus.ABSENT) }, modifier = Modifier.weight(1f)
			) {
				Text("All Absent", style = MaterialTheme.typography.labelSmall)
			}
		}
	}
}
