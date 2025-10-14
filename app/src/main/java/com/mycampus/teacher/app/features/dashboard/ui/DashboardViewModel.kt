package com.mycampus.teacher.app.features.dashboard.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycampus.teacher.app.config.route.Route
import com.mycampus.teacher.app.core.utils.SnackbarManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
	val snackbarManager: SnackbarManager
) : ViewModel() {
	private val _uiState = MutableStateFlow(
		DashboardUiState(
//			recentActivities = listOf(
//				Activity("Grade 10A - Math Quiz completed", "2 hours ago", "success"),
//				Activity("New question added to Question Bank", "3 hours ago", "info"),
//				Activity("Attendance marked for Grade 9B", "5 hours ago", "success"),
//				Activity("Online class scheduled for tomorrow", "1 day ago", "warning")
//			)
		)
	)
	val uiState: StateFlow<DashboardUiState> = _uiState
	
	// Navigation events
	private val _navigationEvents = MutableSharedFlow<String>()
	val navigationEvents = _navigationEvents.asSharedFlow()
	
	
	private fun navigate(route: String) {
		viewModelScope.launch {
			_navigationEvents.emit(route)
		}
	}
}

data class StatItem(
	val title: String, val value: String, val icon: ImageVector, val color: Color
)

data class QuickAction(
	val title: String, val icon: ImageVector, val path: String, val backgroundColor: Color
)

data class Activity(
	val activity: String, val time: String, val type: String
)

data class DashboardUiState(
	
	val stats: List<StatItem> = listOf(
		StatItem("Total Students", "128", Icons.Default.Person, Color(0xFF2563EB)),
		StatItem("Classes Today", "5", Icons.Default.DateRange, Color(0xFF16A34A)),
		StatItem("Pending Marks", "23", Icons.Default.Description, Color(0xFFF97316)),
		StatItem("Online Sessions", "12", Icons.Default.VideoCall, Color(0xFF9333EA))
	),
	
	val quickActions: List<QuickAction> = listOf(
		QuickAction("Mark Attendance", Icons.Default.Person, Route.Attendance.path, Color(0xFF3B82F6)),
		QuickAction("Marks Entry", Icons.Default.Description, Route.MarksEntry.path, Color(0xFF22C55E)),
		QuickAction("Start Class", Icons.Default.VideoCall, Route.OnlineClass.path, Color(0xFFA855F7)),
		QuickAction(
			"Add Questions", Icons.AutoMirrored.Filled.Help, Route.QuestionBank.path, Color(0xFFF97316)
		)
	),
	
	val recentActivities: List<Activity> = emptyList(),
)