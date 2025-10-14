package com.mycampus.teacher.app.features.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {
	var email by remember { mutableStateOf("") }
	var submitted by remember { mutableStateOf(false) }
	
	val snackbarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()
	
	Scaffold(
		snackbarHost = { SnackbarHost(snackbarHostState) }
	) { paddingValues ->
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(
					brush = Brush.linearGradient(
						colors = listOf(
							MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
							MaterialTheme.colorScheme.background,
							MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
						)
					)
				)
				.padding(paddingValues)
				.padding(16.dp),
			contentAlignment = Alignment.Center
		) {
			Card(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 16.dp),
				shape = RoundedCornerShape(16.dp),
				elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
			) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(24.dp),
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.spacedBy(16.dp)
				) {
					// Icon
					Box(
						modifier = Modifier
							.size(64.dp)
							.background(
								brush = Brush.linearGradient(
									colors = listOf(
										MaterialTheme.colorScheme.primary,
										MaterialTheme.colorScheme.secondary
									)
								),
								shape = CircleShape
							),
						contentAlignment = Alignment.Center
					) {
						Icon(
							imageVector = Icons.Default.School,
							contentDescription = "School Icon",
							tint = Color.White,
							modifier = Modifier.size(32.dp)
						)
					}
					
					// Title and Description
					Text(
						text = "Forgot Password",
						style = MaterialTheme.typography.headlineMedium,
						textAlign = TextAlign.Center
					)
					
					Text(
						text = if (submitted) {
							"We've sent you a reset link"
						} else {
							"Enter your email to reset your password"
						},
						style = MaterialTheme.typography.bodyMedium,
						color = MaterialTheme.colorScheme.onSurfaceVariant,
						textAlign = TextAlign.Center
					)
					
					Spacer(modifier = Modifier.height(8.dp))
					
					if (!submitted) {
						// Email Input Form
						Column(
							modifier = Modifier.fillMaxWidth(),
							verticalArrangement = Arrangement.spacedBy(16.dp)
						) {
							OutlinedTextField(
								value = email,
								onValueChange = { email = it },
								label = { Text("Email Address") },
								placeholder = { Text("Enter your email") },
								modifier = Modifier.fillMaxWidth(),
								singleLine = true
							)
							
							Button(
								onClick = {
									submitted = true
									scope.launch {
										snackbarHostState.showSnackbar(
											message = "Check your email for password reset instructions.",
											duration = SnackbarDuration.Short
										)
									}
								},
								modifier = Modifier.fillMaxWidth(),
								shape = RoundedCornerShape(8.dp),
								enabled = email.isNotEmpty()
							) {
								Text(
									"Send Reset Link",
									modifier = Modifier.padding(vertical = 8.dp)
								)
							}
							
							OutlinedButton(
								onClick = { navController.navigateUp() },
								modifier = Modifier.fillMaxWidth(),
								shape = RoundedCornerShape(8.dp)
							) {
								Icon(
									imageVector = Icons.Default.ArrowBack,
									contentDescription = "Back",
									modifier = Modifier.size(16.dp)
								)
								Spacer(modifier = Modifier.width(8.dp))
								Text("Back to Login")
							}
						}
					} else {
						// Success State
						Column(
							modifier = Modifier.fillMaxWidth(),
							verticalArrangement = Arrangement.spacedBy(16.dp)
						) {
							Text(
								text = "If an account exists with $email, you will receive a password reset link shortly.",
								style = MaterialTheme.typography.bodySmall,
								color = MaterialTheme.colorScheme.onSurfaceVariant,
								textAlign = TextAlign.Center,
								modifier = Modifier.fillMaxWidth()
							)
							
							OutlinedButton(
								onClick = { navController.navigateUp() },
								modifier = Modifier.fillMaxWidth(),
								shape = RoundedCornerShape(8.dp)
							) {
								Icon(
									imageVector = Icons.Default.ArrowBack,
									contentDescription = "Back",
									modifier = Modifier.size(16.dp)
								)
								Spacer(modifier = Modifier.width(8.dp))
								Text("Back to Login")
							}
						}
					}
				}
			}
		}
	}
}