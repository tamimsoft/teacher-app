package com.mycampus.teacher.app.core.utils

import androidx.compose.material3.SnackbarDuration
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackbarManager @Inject constructor() {
	private val _messages = MutableSharedFlow<SnackbarEvent>()
	val messages = _messages.asSharedFlow()
	
	suspend fun showMessage(
		text: String,
		actionLabel: String? = null,
		duration: SnackbarDuration = SnackbarDuration.Short,
		onAction: (() -> Unit)? = null
	) {
		_messages.emit(
			SnackbarEvent.Message(
				text = text, actionLabel = actionLabel, duration = duration, onAction = onAction
			)
		)
	}
	
	suspend fun dismiss() {
		_messages.emit(SnackbarEvent.Dismiss)
	}
}
