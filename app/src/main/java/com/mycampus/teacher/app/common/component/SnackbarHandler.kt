package com.mycampus.teacher.app.common.component

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.mycampus.teacher.app.core.utils.SnackbarEvent
import com.mycampus.teacher.app.core.utils.SnackbarManager

@Composable
fun SnackbarHandler(
	snackbarManager: SnackbarManager,
	snackbarHostState: SnackbarHostState) {
	val scope = rememberCoroutineScope()
	
	LaunchedEffect(Unit) {
		snackbarManager.messages.collect { event ->
			when (event) {
				is SnackbarEvent.Message -> {
					val result = snackbarHostState.showSnackbar(
						message = event.text, actionLabel = event.actionLabel, duration = event.duration
					)
					if (result == SnackbarResult.ActionPerformed) {
						event.onAction?.invoke()
					}
				}
				
				SnackbarEvent.Dismiss -> {
					snackbarHostState.currentSnackbarData?.dismiss()
				}
			}
		}
	}
}
