package com.mycampus.teacher.app.features.exam.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycampus.teacher.app.features.exam.domain.model.StudentMark
import com.mycampus.teacher.app.features.exam.ui.getGrade


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarksTextField(
	student: StudentMark,
	index: Int,
	students: List<StudentMark>,
	onUpdate: (List<StudentMark>) -> Unit,
	focusRequesters: List<FocusRequester>,
	focusManager: FocusManager
) {
	val value = student.marks ?: ""
	val state = rememberTextFieldState(value)
	
	// Sync from model to UI
	LaunchedEffect(value) {
		if (state.text.toString() != value) {
			state.edit {
				replace(0, length, value)
			}
		}
	}
	
	OutlinedTextField(
		state = state,
		modifier = Modifier
			.width(50.dp)
			.height(34.dp)
			.focusRequester(focusRequesters[index])
			.defaultMinSize(minHeight = 0.dp),
		textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
		lineLimits = TextFieldLineLimits.SingleLine,
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Decimal,
			imeAction = if (index == students.lastIndex) ImeAction.Done else ImeAction.Next
		),
		onKeyboardAction = {
			if (index < focusRequesters.lastIndex) {
				focusRequesters[index + 1].requestFocus()
			} else {
				focusManager.clearFocus()
			}
		},
		// Input filter logic
		inputTransformation = InputTransformation {
			val newText = asCharSequence().toString()
			//✅ Valid examples  "0", "5.5", "99.99", "100"
			val regex = Regex("^(100|(\\d{0,2})(\\.\\d{0,2})?)$")
			
			if (newText.length <= 5 && newText.matches(regex)) {
				// allowed input, so do nothing (accept)
			} else {
				// reject invalid input by restoring previous
				revertAllChanges()
			}
		},
		// Optional: compact padding for table cells
		contentPadding = PaddingValues(6.dp))
	
	// React to text changes to update model
	LaunchedEffect(state.text) {
		val input = state.text.toString()
		
		if (input.isBlank()) {
			onUpdate(students.map { s ->
				if (s.id == student.id) s.copy(marks = null, grade = "", status = "")
				else s
			})
		} else {
			input.toFloatOrNull()?.takeIf { it in 0.0..100.0 }?.let { value ->
				onUpdate(students.map { s ->
					if (s.id == student.id) {
						s.copy(
							marks = value.toInt().toString(),
							grade = getGrade(value),
							status = if (value >= 50) "pass" else "fail"
						)
					} else s
				})
			}
		}
	}
}
