package com.mycampus.teacher.app.features.dashboard.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.dashboard.ui.StatItem

@Composable
fun DashboardStatCard(
	stat: StatItem,
	modifier: Modifier = Modifier
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
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(12.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Column(
				verticalArrangement = Arrangement.spacedBy(4.dp)
			) {
				Text(
					text = stat.title,
					style = MaterialTheme.typography.bodySmall,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
				Text(
					text = stat.value,
					style = MaterialTheme.typography.headlineMedium,
					fontWeight = FontWeight.Bold
				)
			}
			Icon(
				imageVector = stat.icon,
				contentDescription = stat.title,
				tint = stat.color,
				modifier = Modifier.size(32.dp)
			)
		}
	}
}