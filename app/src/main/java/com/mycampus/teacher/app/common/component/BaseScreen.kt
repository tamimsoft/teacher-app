package com.mycampus.teacher.app.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mycampus.teacher.app.core.utils.SnackbarManager

@Composable
fun BaseScreen(
	title: String,
	subtitle: String? = null,
	isHome: Boolean = false,
	avatarUrl: String? = null,
	popBackStack: (() -> Unit)? = null,
	navigationIcon: @Composable (() -> Unit)? = null,
	actions: @Composable (RowScope.() -> Unit)? = null,
	snackbarManager: SnackbarManager,
	content: @Composable ((PaddingValues) -> Unit)
) {
	val snackbarHostState = remember { SnackbarHostState() }
	
	Scaffold(topBar = {
		AppBar(
			title = title, subtitle = subtitle, isHome = isHome,
			avatarUrl = avatarUrl,
			navigationIcon = {
				popBackStack?.let{
					IconButton(
						onClick = popBackStack
					) {
						Icon(
							imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Localized description"
						)
					}
				}
			}, actions = actions
		)
	}, snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
		SnackbarHandler(snackbarManager, snackbarHostState)
		content(padding)
	}
}
