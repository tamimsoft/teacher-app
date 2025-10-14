package com.mycampus.teacher.app.features.dashboard.ui.component

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
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.dashboard.ui.Activity

@Composable
fun ActivityItem(activity: Activity) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.background(
				color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
				shape = RoundedCornerShape(8.dp)
			)
			.padding(12.dp),
		horizontalArrangement = Arrangement.spacedBy(12.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Box(
			modifier = Modifier
				.size(8.dp)
				.background(
					color = when (activity.type) {
						"success" -> Color(0xFF22C55E)
						"warning" -> Color(0xFFF59E0B)
						else -> Color(0xFF3B82F6)
					}, shape = CircleShape
				)
		)
		
		Column(
			modifier = Modifier.weight(1f),
			verticalArrangement = Arrangement.spacedBy(4.dp)
		) {
			Text(
				text = activity.activity,
				style = MaterialTheme.typography.bodySmall,
				color = MaterialTheme.colorScheme.onSurface
			)
			Row(
				horizontalArrangement = Arrangement.spacedBy(4.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Icon(
					imageVector = Icons.Default.Schedule,
					contentDescription = "Time",
					modifier = Modifier.size(12.dp),
					tint = MaterialTheme.colorScheme.onSurfaceVariant
				)
				Text(
					text = activity.time,
					style = MaterialTheme.typography.labelSmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
		}
	}
}
