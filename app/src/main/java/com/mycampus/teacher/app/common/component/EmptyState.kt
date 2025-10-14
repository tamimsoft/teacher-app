package com.mycampus.teacher.app.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EmptyState(
	modifier: Modifier = Modifier,
	icon: ImageVector? = Icons.Default.Info, // Optional icon
	title: String = "Nothing here yet",
	description: String? = null,
	buttonText: String? = null,
	onButtonClick: (() -> Unit)? = null,
	contentAlignment: Alignment = Alignment.Center
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(32.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		if (icon != null) {
			Icon(
				imageVector = icon,
				contentDescription = null,
				tint = MaterialTheme.colorScheme.primary,
				modifier = Modifier
					.size(72.dp)
					.padding(bottom = 16.dp)
			)
		}
		
		Text(
			text = title,
			style = MaterialTheme.typography.titleMedium,
			textAlign = TextAlign.Center
		)
		
		description?.let {
			Spacer(modifier = Modifier.height(8.dp))
			Text(
				text = it,
				style = MaterialTheme.typography.bodyMedium,
				textAlign = TextAlign.Center,
				color = MaterialTheme.colorScheme.onSurfaceVariant
			)
		}
		
		if (buttonText != null && onButtonClick != null) {
			Spacer(modifier = Modifier.height(24.dp))
			Button(onClick = onButtonClick) {
				Text(buttonText)
			}
		}
	}
}
