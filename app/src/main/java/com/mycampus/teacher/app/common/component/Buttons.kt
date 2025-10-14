package com.mycampus.teacher.app.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mycampus.teacher.app.core.theme.appButton

enum class ButtonSize { Small, Medium, Large }
enum class ButtonVariant { Primary, Secondary, Surface, Error }

@Composable
fun AppButton(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	size: ButtonSize = ButtonSize.Medium,
	variant: ButtonVariant = ButtonVariant.Primary,
	isBorder: Boolean = false,
	isLoading: Boolean = false,
	prefixIcon: ImageVector? = null,
	postfixIcon: ImageVector? = null,
	text: String
) {
	Button(
		onClick = onClick,
		modifier = modifier.appButton(size),
		enabled = enabled && !isLoading,
		colors = buttonColorsForVariant(variant),
		border = isBorder.let { ButtonDefaults.outlinedButtonBorder(enabled) },
		shape = MaterialTheme.shapes.medium,
		contentPadding = paddingValues(size)
	) {
		// Loading state - show only progress indicator
		if (isLoading) {
			CircularProgressIndicator(
				modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = buttonContentColor(variant)
			)
		} else {
			// Normal state - show content with optional icons
			Row(
				verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
			) {
				// Prefix Icon
				prefixIcon?.let { icon ->
					BuildIcon(icon, size, variant)
					Spacer(modifier = Modifier.width(8.dp))
				}
				
				// Text
				Text(
					text = text, style = textStyle(size), color = buttonContentColor(variant)
				)
				
				// Postfix Icon
				postfixIcon?.let { icon ->
					Spacer(modifier = Modifier.width(8.dp))
					BuildIcon(icon, size, variant)
				}
			}
		}
	}
}


// Helper functions
@Composable
private fun buttonColorsForVariant(variant: ButtonVariant) = when (variant) {
	ButtonVariant.Primary -> ButtonDefaults.buttonColors(
		containerColor = MaterialTheme.colorScheme.primary,
		contentColor = MaterialTheme.colorScheme.onPrimary
	)
	
	ButtonVariant.Secondary -> ButtonDefaults.buttonColors(
		containerColor = MaterialTheme.colorScheme.secondary,
		contentColor = MaterialTheme.colorScheme.onSecondary
	)
	
	ButtonVariant.Surface -> ButtonDefaults.buttonColors(
		containerColor = MaterialTheme.colorScheme.surface,
		contentColor = MaterialTheme.colorScheme.onSurface
	)
	
	ButtonVariant.Error -> ButtonDefaults.outlinedButtonColors(
		contentColor = MaterialTheme.colorScheme.primary
	)
}

@Composable
private fun buttonContentColor(variant: ButtonVariant) = when (variant) {
	ButtonVariant.Primary -> MaterialTheme.colorScheme.onPrimary
	ButtonVariant.Secondary -> MaterialTheme.colorScheme.onSecondary
	ButtonVariant.Surface -> MaterialTheme.colorScheme.onSurface
	ButtonVariant.Error -> MaterialTheme.colorScheme.primary
}

@Composable
private fun BuildIcon(icon: ImageVector, size: ButtonSize, variant: ButtonVariant) = Icon(
	imageVector = icon,
	contentDescription = null,
	modifier = Modifier.size(buttonIconSize(size)),
	tint = buttonContentColor(variant)
)

@Composable
private fun textStyle(size: ButtonSize) = when (size) {
	ButtonSize.Small -> MaterialTheme.typography.labelMedium
	ButtonSize.Medium -> MaterialTheme.typography.labelLarge
	ButtonSize.Large -> MaterialTheme.typography.titleSmall
}

private fun buttonIconSize(size: ButtonSize) = when (size) {
	ButtonSize.Small -> 16.dp
	ButtonSize.Medium -> 20.dp
	ButtonSize.Large -> 24.dp
}

@Composable
private fun paddingValues(size: ButtonSize) = when (size) {
	ButtonSize.Small -> PaddingValues(horizontal = 16.dp, vertical = 8.dp)
	ButtonSize.Medium -> PaddingValues(horizontal = 24.dp, vertical = 12.dp)
	ButtonSize.Large -> PaddingValues(horizontal = 32.dp, vertical = 16.dp)
}
