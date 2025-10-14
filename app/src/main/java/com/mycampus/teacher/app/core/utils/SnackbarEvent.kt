package com.mycampus.teacher.app.core.utils

import androidx.compose.material3.SnackbarDuration

sealed class SnackbarEvent {
	data class Message(
		val text: String,
		val actionLabel: String? = null,
		val duration: SnackbarDuration = SnackbarDuration.Short,
		val onAction: (() -> Unit)? = null
	) : SnackbarEvent()
	
	object Dismiss : SnackbarEvent()
}
