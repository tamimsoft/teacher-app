package com.mycampus.teacher.app.features.auth.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mycampus.teacher.app.common.component.AppButton
import com.mycampus.teacher.app.common.component.SnackbarHandler
import com.mycampus.teacher.app.features.auth.ui.component.SchoolBottomSheetDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
	vm: LoginViewModel = hiltViewModel(), onLoginSuccess: () -> Unit
) {
	
	val snackbarHostState = remember { SnackbarHostState() }
	val uiState by vm.uiState.collectAsState()
	// Observe navigation state
	LaunchedEffect(uiState.navigateToDashboard) {
		if (uiState.navigateToDashboard) {
			onLoginSuccess()
			vm.resetNavigationFlag()
		}
	}
	
	val focusManager = LocalFocusManager.current
	var passwordVisible by remember { mutableStateOf(false) }
	
	
	Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
		
		SnackbarHandler(vm.snackbarManager, snackbarHostState)
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState())
				.padding(paddingValues)
				.padding(horizontal = 24.dp),
			
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
								MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary
							)
						), shape = CircleShape
					), contentAlignment = Alignment.Center
			) {
				Icon(
					imageVector = Icons.Default.School,
					contentDescription = "School Icon",
					tint = Color.White,
					modifier = Modifier.size(32.dp)
				)
			}
			
			// Title
			Text(
				text = "Teacher Portal",
				style = MaterialTheme.typography.headlineMedium,
				textAlign = TextAlign.Center
			)
			
			Text(
				text = "Sign in to access your dashboard",
				style = MaterialTheme.typography.bodyMedium,
				color = MaterialTheme.colorScheme.onSurfaceVariant,
				textAlign = TextAlign.Center
			)
			
			Spacer(modifier = Modifier.height(8.dp))
			
			// School Dropdown
			SchoolBottomSheetDropdown(
				icon= Icons.Default.School,
				items = uiState.schools,
				selectedItem = uiState.selectedSchool,
				onItemSelected = vm::onSchoolSelected,
				onScrollUp= vm::onScrollUp
			)
			
			// Login ID
			OutlinedTextField(
				value = uiState.loginId,
				onValueChange = vm::updateLoginId,
				label = { Text("Login ID") },
				placeholder = { Text("Enter your login ID") },
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Person, contentDescription = "Login ID"
					)
				},
				modifier = Modifier.fillMaxWidth(),
				singleLine = true,
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
				),
				
				keyboardActions = KeyboardActions(
					onNext = { focusManager.moveFocus(FocusDirection.Next) })
			
			)
			
			// Password
			OutlinedTextField(
				value = uiState.password,
				onValueChange = vm::updatePassword,
				label = { Text("Password") },
				placeholder = { Text("Enter your password") },
				visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
				leadingIcon = {
					Icon(
						imageVector = Icons.Default.Fingerprint, contentDescription = "password"
					)
				},
				trailingIcon = {
					val image = if (passwordVisible) Icons.Default.Visibility
					else Icons.Default.VisibilityOff
					
					IconButton(onClick = { passwordVisible = !passwordVisible }) {
						Icon(
							imageVector = image,
							contentDescription = if (passwordVisible) "Hide password" else "Show password"
						)
					}
				},
				modifier = Modifier.fillMaxWidth(),
				singleLine = true,
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
				),
				
				keyboardActions = KeyboardActions(
					onDone = { focusManager.clearFocus() })
			)
			
			// Forgot Password
			TextButton(
				onClick = { /* Navigate to forgot password */ }, modifier = Modifier.align(Alignment.End)
			) {
				Text("Forgot password?")
			}
			
			// Sign In Button
			AppButton(
				text = "Sign In",
				isLoading = uiState.isLoading,
				enabled = uiState.isFormValid,
				postfixIcon = Icons.AutoMirrored.Default.ArrowForward,
				onClick = { vm.login() },
			)
		}
	}
}
