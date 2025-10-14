package com.mycampus.teacher.app.features.profile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SettingItem(
	title: String,
	description: String,
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		Column {
			Text(title, fontWeight = FontWeight.Medium)
			Text(description, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
		}
		Switch(checked = checked, onCheckedChange = onCheckedChange)
	}
}

