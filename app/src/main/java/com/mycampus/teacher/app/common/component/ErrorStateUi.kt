package com.mycampus.teacher.app.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorStateUI(
	errorMessage: String, onRetry: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(32.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Icon(
			imageVector = Icons.Default.ErrorOutline,
			contentDescription = "Error",
			modifier = Modifier.size(64.dp),
			tint = MaterialTheme.colorScheme.error
		)
		Spacer(modifier = Modifier.height(16.dp))
		Text(
			text = errorMessage,
			style = MaterialTheme.typography.bodyMedium,
			color = MaterialTheme.colorScheme.error,
			textAlign = TextAlign.Center
		)
		Spacer(modifier = Modifier.height(16.dp))
		Button(
			onClick = onRetry, colors = ButtonDefaults.buttonColors(
				containerColor = MaterialTheme.colorScheme.errorContainer,
				contentColor = MaterialTheme.colorScheme.onErrorContainer
			)
		) {
			Text("Retry")
		}
	}
}
