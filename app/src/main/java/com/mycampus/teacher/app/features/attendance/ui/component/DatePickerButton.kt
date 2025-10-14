package com.mycampus.teacher.app.features.attendance.ui.component

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.mycampus.teacher.app.common.component.AppButton
import com.mycampus.teacher.app.common.component.ButtonVariant
import java.time.LocalDate

@Composable
fun DatePickerButton(date: LocalDate, onDateChange: (LocalDate) -> Unit) {
	val context = LocalContext.current
	
	val year = date.year
	val month = date.monthValue - 1
	val day = date.dayOfMonth
	
	val datePicker = remember {
		DatePickerDialog(
			context, { _: DatePicker, y: Int, m: Int, d: Int ->
				onDateChange(LocalDate.of(y, m + 1, d))
			}, year, month, day
		)
	}
	AppButton(
		variant = ButtonVariant.Surface,
		isBorder = true,
		onClick = { datePicker.show() },
		prefixIcon = Icons.Default.CalendarToday,
		text = "${date.dayOfMonth}/${date.monthValue}/${date.year}"
	)
}
