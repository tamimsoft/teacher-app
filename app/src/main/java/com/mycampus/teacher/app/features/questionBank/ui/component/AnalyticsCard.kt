package com.mycampus.teacher.app.features.questionBank.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnalyticsCard(title: String, count: Int, modifier: Modifier = Modifier) {
	Card(shape = RoundedCornerShape(12.dp), modifier = modifier) {
		Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
			Text(
				count.toString(),
				fontSize = 24.sp,
				fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
			)
			Text(title, color = Color.Gray)
		}
	}
}