package com.mycampus.teacher.app.config.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mycampus.teacher.app.features.attendance.ui.AttendanceScreen
import com.mycampus.teacher.app.features.auth.ui.LoginScreen
import com.mycampus.teacher.app.features.dashboard.ui.DashboardScreen
import com.mycampus.teacher.app.features.exam.ui.MarksEntryScreen

@Composable
fun AppRoute(
	navController: NavHostController = rememberNavController(),
	startRoute: String = Route.Login.path

) {
	
	NavHost(navController = navController, startDestination = startRoute) {
		
		composable(route = Route.Login.path) {
			LoginScreen(
				onLoginSuccess= {
					navController.navigate(Route.Dashboard.path)
				}
			)
		}
		
		composable(route = Route.Dashboard.path) {
			DashboardScreen(
				navController = navController
			)
		}
		composable(route = Route.Attendance.path) {
			AttendanceScreen(
				navController = navController
			)
		}
		composable(route = Route.MarksEntry.path) {
			MarksEntryScreen(
				navController = navController
			)
		}
//
//		composable(
//			route = Route.ProductDetail.route,
//			arguments = listOf(navArgument("id") { type = NavType.IntType })
//		) { backStack ->
//			val id = backStack.arguments?.getInt("id") ?: 0
//			ProductDetailScreen(
//				productId = id, onBack = { navController.popBackStack() })
//		}
//
//		composable(route = Route.Cart.route) {
//			CartScreen(onBack = { navController.popBackStack() })
//		}
	}
}
