package com.mycampus.teacher.app.features.dashboard.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.features.dashboard.ui.QuickAction

@Composable
fun QuickActionButton(
	action: QuickAction,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	OutlinedButton(
		onClick = onClick,
		modifier = modifier.height(80.dp),
		shape = RoundedCornerShape(8.dp),
		colors = ButtonDefaults.outlinedButtonColors(
			containerColor = Color.Transparent
		)
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Box(
				modifier = Modifier
					.size(32.dp)
					.background(
						color = action.backgroundColor, shape = CircleShape
					),
				contentAlignment = Alignment.Center
			) {
				Icon(
					imageVector = action.icon,
					contentDescription = action.title,
					tint = Color.White,
					modifier = Modifier.size(16.dp)
				)
			}
			Text(
				text = action.title,
				style = MaterialTheme.typography.labelSmall,
				color = MaterialTheme.colorScheme.onSurface
			)
		}
	}
}
