package com.mycampus.teacher.app.common.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.mycampus.teacher.app.core.theme.appTextField

fun leadingIcon() {
	TODO("Not yet implemented")
}

@Composable
fun AppTextField(
	value: String,
	onValueChange: (String) -> Unit,
	modifier: Modifier = Modifier,
	label: String,
	enabled: Boolean = true,
	isError: Boolean = false,
	prefixIcon: ImageVector? = null,
	postfixIcon: ImageVector? = null,
	supportingText: String? = null
) {
	OutlinedTextField(
		value = value,
		onValueChange = onValueChange,
		modifier = modifier.appTextField(),
		enabled = enabled,
		label = { Text(label) },
		isError = isError,
		supportingText = {
			if (supportingText != null) {
				Text(supportingText)
			}
		},
		
		leadingIcon = prefixIcon?.let { icon ->
			{ Icon(imageVector = icon, contentDescription = null) }
		},
		trailingIcon = postfixIcon?.let { icon ->
			{ Icon(imageVector = icon, contentDescription = null) }
		},
		colors = TextFieldDefaults.colors(
			focusedContainerColor = MaterialTheme.colorScheme.surface,
			unfocusedContainerColor = MaterialTheme.colorScheme.surface,
			focusedIndicatorColor = MaterialTheme.colorScheme.primary,
			unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
			focusedLabelColor = MaterialTheme.colorScheme.primary,
		),
		shape = MaterialTheme.shapes.small
	)
}
