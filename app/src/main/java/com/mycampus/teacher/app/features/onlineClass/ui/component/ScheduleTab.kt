package com.mycampus.teacher.app.features.onlineClass.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun ScheduleTab(selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit, onSchedule: () -> Unit) {
	var title by remember { mutableStateOf("") }
	var subject by remember { mutableStateOf("") }
	var className by remember { mutableStateOf("") }
	var time by remember { mutableStateOf("") }
	
	Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
		OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
		OutlinedTextField(value = subject, onValueChange = { subject = it }, label = { Text("Subject") })
		OutlinedTextField(value = className, onValueChange = { className = it }, label = { Text("Class Name") })
		OutlinedTextField(value = time, onValueChange = { time = it }, label = { Text("Time") })
		Button(onClick = onSchedule, modifier = Modifier.fillMaxWidth()) {
			Text("Schedule Class")
		}
	}
}
