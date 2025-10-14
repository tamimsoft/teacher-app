package com.mycampus.teacher.app.features.attendance.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AttendanceTabs(
	selectedTab: Int, onTabSelected: (Int) -> Unit
) {
	val tabs = listOf("Section Wise", "Subject Wise")
	val icons = listOf(Icons.Default.School, Icons.Default.Book)
	
	PrimaryTabRow(selectedTabIndex = selectedTab) {
		tabs.forEachIndexed { index, title ->
			Tab(selected = selectedTab == index, onClick = { onTabSelected(index) }, text = {
				Row(verticalAlignment = Alignment.CenterVertically) {
					Icon(
						imageVector = icons[index], contentDescription = null, modifier = Modifier.size(16.dp)
					)
					Spacer(Modifier.width(4.dp))
					Text(title)
				}
			})
		}
	}
}