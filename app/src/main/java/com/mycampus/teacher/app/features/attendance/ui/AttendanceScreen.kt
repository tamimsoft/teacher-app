package com.mycampus.teacher.app.features.attendance.ui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mycampus.teacher.app.common.component.BaseScreen
import com.mycampus.teacher.app.features.attendance.ui.component.AttendanceSummary
import com.mycampus.teacher.app.features.attendance.ui.component.AttendanceTabs
import com.mycampus.teacher.app.features.attendance.ui.component.QuickActions
import com.mycampus.teacher.app.features.attendance.ui.component.SaveButton
import com.mycampus.teacher.app.features.attendance.ui.component.SelectionCardAtt
import com.mycampus.teacher.app.features.attendance.ui.component.StudentList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreen(
	navController: NavController, vm: AttendanceViewModel = hiltViewModel()
) {
	val uiState by vm.uiState.collectAsState()
	val scope = rememberCoroutineScope()
	
	
	BaseScreen(
		title = "Attendance",
		subtitle = "Mark daily attendance for your classes.",
		popBackStack = { navController.popBackStack() },
		snackbarManager = vm.snackbarManager
	) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
				.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			AttendanceTabs(selectedTab = uiState.selectedTab, onTabSelected = { vm.updateTab(it) })
			
			SelectionCardAtt(
				uiState = uiState,
				onSectionSelected = vm::selectClass,
				onSubjectSelected = vm::selectSubject,
				onDateChange = vm::selectDate
			)
			
			if (uiState.selectedSection.isNotEmpty()) {
				AttendanceSummary(uiState = uiState)
				
				QuickActions(onMarkAll = { status -> vm.markAllStatus(status) })
				
				StudentList(
					students = uiState.students,
					onStatusChange = { id, status -> vm.updateStudentStatus(id, status) })
				
				SaveButton(onSave = vm::saveAttendance)
				Spacer(modifier = Modifier)
			}
		}
	}
	
}

