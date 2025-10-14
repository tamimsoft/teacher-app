package com.mycampus.teacher.app.features.exam.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExamStatCard(
	modifier: Modifier = Modifier,
	icon: androidx.compose.ui.graphics.vector.ImageVector,
	value: String,
	label: String,
	iconTint: Color
) {
	Card(
		modifier = modifier,
		shape = RoundedCornerShape(12.dp),
		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.onSecondary
		),
		elevation = CardDefaults.cardElevation(
			0.5.dp
		)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Icon(
				imageVector = icon,
				contentDescription = null,
				modifier = Modifier.size(32.dp),
				tint = iconTint
			)
			Text(
				text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold
			)
			Text(
				text = label, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant
			)
		}
	}
}