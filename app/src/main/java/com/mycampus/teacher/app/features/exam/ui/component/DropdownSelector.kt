//package com.mycampus.teacher.app.features.exam.ui.component
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun <T> DropdownSelector(
//	label: String,
//	value: T,
//	items: List<T>,
//	onChange: (T) -> Unit,
//	itemToText: (T) -> String,
//	modifier: Modifier = Modifier
//) {
//	var expanded by remember { mutableStateOf(false) }
//
//	Column(modifier = modifier) {
//		Text(
//			text = label,
//			fontSize = 12.sp,
//			fontWeight = FontWeight.Medium
//		)
//
//		Box {
//			OutlinedTextField(
//				value = itemToText(value),
//				onValueChange = {}, // readOnly, so this won't be called
//				readOnly = true,
//				placeholder = { Text("Select $label") },
//				modifier = Modifier
//					.width(160.dp)
//					.clickable { expanded = true }
//			)
//
//			DropdownMenu(
//				expanded = expanded,
//				onDismissRequest = { expanded = false }
//			) {
//				items.forEach { item ->
//					DropdownMenuItem(
//						text = { Text(itemToText(item)) },
//						onClick = {
//							onChange(item)
//							expanded = false
//						}
//					)
//				}
//			}
//		}
//	}
//}
//
//
//
//@Composable
//fun DropdownSelector_off(label: String, value: String, items: List<String>, onChange: (String) -> Unit) {
//	var expanded by remember { mutableStateOf(false) }
//	Column {
//		Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium)
//		Box {
//			OutlinedTextField(
//				value = value,
//				onValueChange = {},
//				modifier = Modifier
//					.width(160.dp)
//					.clickable { expanded = true },
//				readOnly = true,
//				placeholder = { Text("Select $label") }
//			)
//			DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
//				items.forEach {
//					DropdownMenuItem(
//						text = { Text(it) },
//						onClick = {
//							onChange(it)
//							expanded = false
//						}
//					)
//				}
//			}
//		}
//	}
//}
