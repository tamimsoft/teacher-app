package com.mycampus.teacher.app.features.questionBank.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun DropdownMenuBox(selectedItem: String, options: List<String>, onItemSelected: (String) -> Unit) {
	var expanded by remember { mutableStateOf(false) }
	Box {
		Button(onClick = { expanded = true }) { Text(selectedItem) }
		DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
			options.forEach { option ->
				DropdownMenuItem(
					text = { Text(option) },
					onClick = { onItemSelected(option); expanded = false })
			}
		}
	}
}