package com.mycampus.teacher.app.features.onlineClass.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.mycampus.teacher.app.features.onlineClass.domain.model.Status

@Composable
fun StatusBadge(status: Status) {
	val (text, color) = when (status) {
		Status.Live -> "Live" to Color.Red
		Status.Upcoming -> "Upcoming" to Color.Blue
		Status.Ended -> "Ended" to Color.Gray
	}
	Text(text, color = color, fontWeight = FontWeight.Bold)
}
