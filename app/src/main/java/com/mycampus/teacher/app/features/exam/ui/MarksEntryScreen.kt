package com.mycampus.teacher.app.features.exam.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mycampus.teacher.app.common.component.BaseScreen
import com.mycampus.teacher.app.features.dashboard.ui.component.SelectionCardExam
import com.mycampus.teacher.app.features.exam.ui.component.AnalyticsTab
import com.mycampus.teacher.app.features.exam.ui.component.MarksEntryTab

@Composable
fun MarksEntryScreen(
	navController: NavController, vm: MarksEntryViewModel = hiltViewModel()
) {

	val uiState by vm.uiState.collectAsState()
	val focusManager = LocalFocusManager.current

	BaseScreen(
		title = "Exam Marks Entry",
		subtitle = "Enter and manage student exam scores.",
		popBackStack = { navController.popBackStack() },
		snackbarManager = vm.snackbarManager
	) { paddingValues ->
		Column(
			modifier = Modifier
				.clickable(
					interactionSource = remember { MutableInteractionSource() },
					indication = null
				) {
					focusManager.clearFocus()
				}
				.fillMaxSize()
				.padding(paddingValues)
				.padding(vertical = 16.dp)
				.verticalScroll(rememberScrollState()),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {

			// Selections
			SelectionCardExam(
				uiState = uiState,
				onSectionSelected = vm::selectSection,
				onExamSelected = vm::selectExam,
				onSubjectSelected = vm::selectSubject,
				onAssessmentSelected = vm::selectAssessment
			)
			if (vm.isFormValid()) {
				SecondaryTabRow(
					selectedTabIndex = uiState.selectedTab,
					modifier = Modifier.padding(horizontal = 16.dp)
					) {
					Tab(
						modifier = Modifier.padding(vertical = 8.dp),
						selected = uiState.selectedTab == 0,
						onClick = { vm.updateTab(0) }) { Text("Marks Entry") }
					Tab(
						modifier = Modifier.padding(vertical = 8.dp),
						selected = uiState.selectedTab == 1,
						onClick = { vm.updateTab(1)}) { Text("Analytics") }
				}

				when (uiState.selectedTab) {
					0 -> MarksEntryTab(uiState) { updated -> vm.updateStudentsMarks(updated) }
					1 -> AnalyticsTab(uiState.studentsWithMarks)
				}
			}
		}
	}
}


fun getGrade(marks: Float): String = when {
	marks >= 90 -> "A+"
	marks >= 80 -> "A"
	marks >= 70 -> "B+"
	marks >= 60 -> "B"
	marks >= 50 -> "C"
	else -> "F"
}