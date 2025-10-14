package com.mycampus.teacher.app.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
	label: String,
	value: String,
	items: List<String>,
	onItemSelected: (String) -> Unit,
	modifier: Modifier = Modifier,
	leadingIcon: @Composable (() -> Unit)? = null
) {
	var expanded by remember { mutableStateOf(false) }
	Column(modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
		
		ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
			OutlinedTextField(
				value = value,
				onValueChange = {},
				readOnly = true,
				placeholder = { Text("Select $label") },
				leadingIcon = leadingIcon,
				trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
				modifier = Modifier
					.fillMaxWidth()
					.menuAnchor(
						ExposedDropdownMenuAnchorType.PrimaryEditable, enabled = true
					)
			)
			ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
				items.forEach { item ->
					DropdownMenuItem(
						text = { Text(item) },
						onClick = {
							onItemSelected(item)
							expanded = false
						},
					)
				}
			}
		}
	}
}
