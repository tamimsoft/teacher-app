package com.mycampus.teacher.app.features.auth.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties


@Composable
fun SearchableDropdown(
    label: String = "Select One",
    icon: ImageVector,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(selectedItem) }
    val focusManager = LocalFocusManager.current
    val filteredItems = remember(searchQuery, items) {
        items.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (!expanded) expanded = true
            },
            label = { Text(label) },
            leadingIcon = { Icon(icon, contentDescription = null) },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        expanded = true
                    }
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(top = 4.dp),
            properties = PopupProperties(focusable = false) // 👈 keep focus in TextField
        ) {
            if (filteredItems.isEmpty()) {
                Text(
                    text = "No results found",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                filteredItems.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            searchQuery = item
                            onItemSelected(item)
                            expanded = false
                            focusManager.clearFocus() // dismiss keyboard
                        }
                    )
                }
            }
        }
    }
}