package com.mycampus.teacher.app.features.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mycampus.teacher.app.common.component.BaseScreen
import com.mycampus.teacher.app.common.component.EmptyState
import com.mycampus.teacher.app.core.theme.AppTheme
import com.mycampus.teacher.app.features.dashboard.ui.component.ActivityItem
import com.mycampus.teacher.app.features.dashboard.ui.component.DashboardStatCard
import com.mycampus.teacher.app.features.dashboard.ui.component.QuickActionButton

@Composable
fun DashboardScreen(navController: NavController, vm: DashboardViewModel = hiltViewModel()) {
	val scrollState = rememberScrollState()
	
	val uiState by vm.uiState.collectAsState()
	
	
	
	BaseScreen(
		title = "Tamim Hasan", subtitle = "Math Teacher", isHome = true,
//		avatarUrl = ,
		snackbarManager = hiltViewModel<DashboardViewModel>().snackbarManager
	) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(scrollState)
				.padding(paddingValues)
				.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			// Header
			Column(
				modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)
			) {
				Text(
					text = "Welcome Back!",
					style = MaterialTheme.typography.headlineSmall,
					fontWeight = FontWeight.Bold
				)
				Text(
					text = "Here's what's happening with your classes today.",
					style = MaterialTheme.typography.bodyMedium,
					color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
			
			// Stats Grid
			Row(
				modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				uiState.stats.take(2).forEach { stat ->
					DashboardStatCard(
						stat = stat, modifier = Modifier.weight(1f)
					)
				}
			}
			
			Row(
				modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				uiState.stats.drop(2).forEach { stat ->
					DashboardStatCard(
						stat = stat, modifier = Modifier.weight(1f)
					)
				}
			}
			
			// Quick Actions
			Column(
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				Text(
					text = "Quick Actions",
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.SemiBold
				)
				
				Row(
					modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					uiState.quickActions.take(2).forEach { action ->
						QuickActionButton(
							action = action,
							onClick = { navController.navigate(action.path) },
							modifier = Modifier.weight(1f)
						)
					}
				}
				
				Row(
					modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					uiState.quickActions.drop(2).forEach { action ->
						QuickActionButton(
							action = action,
							onClick = { navController.navigate(action.path) },
							modifier = Modifier.weight(1f)
						)
					}
				}
			}
			
			// Recent Activities
			Column(
				verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				Text(
					text = "Recent Activities",
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.SemiBold
				)
				
				Column(
					verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
					if (uiState.recentActivities.isEmpty()) {
						Card(
							elevation = CardDefaults.cardElevation(
								0.5.dp
							),
						) {
							EmptyState()
						}
						return@Column
					}
					uiState.recentActivities.forEach { activity ->
						ActivityItem(activity = activity)
					}
				}
			}
		}
	}
}


@Preview(showBackground = true)
@Composable
fun PreviewDashboardScreen() {
	AppTheme {
		DashboardScreen(
			navController = rememberNavController()
		)
	}
}


