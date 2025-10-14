package com.mycampus.teacher.app.features.attendance.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveButton(onSave: () -> Unit) {
	Button(
		onClick = onSave,
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 16.dp),
		shape = RoundedCornerShape(8.dp)
	) {
		Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(16.dp))
		Spacer(Modifier.width(8.dp))
		Text("Save Attendance", modifier = Modifier.padding(vertical = 8.dp))
	}
}
