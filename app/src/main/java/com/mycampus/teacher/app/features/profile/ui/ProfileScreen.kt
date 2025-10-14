package com.mycampus.teacher.app.features.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycampus.teacher.app.features.profile.domain.model.Teacher
import com.mycampus.teacher.app.features.profile.ui.component.SettingItem
import com.mycampus.teacher.R

@Composable
fun ProfileScreen() {
	val teacherData = remember {
		mutableStateOf(
			Teacher(
				name = "Ms. Sarah Johnson",
				email = "sarah.johnson@school.edu",
				phone = "+1 (555) 123-4567",
				address = "123 Education Lane, Learning City, LC 12345",
				subjects = listOf("Mathematics", "Statistics", "Algebra"),
				experience = "8 years",
				education = "Master's in Mathematics Education",
				joinDate = "September 2016"
			)
		)
	}
	
	var fullName by remember { mutableStateOf(teacherData.value.name) }
	var email by remember { mutableStateOf(teacherData.value.email) }
	var phone by remember { mutableStateOf(teacherData.value.phone) }
	var address by remember { mutableStateOf(teacherData.value.address) }
	var experience by remember { mutableStateOf(teacherData.value.experience) }
	var education by remember { mutableStateOf(teacherData.value.education) }
	var bio by remember { mutableStateOf("") }
	
	var pushNotifications by remember { mutableStateOf(true) }
	var emailNotifications by remember { mutableStateOf(true) }
	var darkMode by remember { mutableStateOf(false) }
	var autoSaveAttendance by remember { mutableStateOf(true) }
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		// Header
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			Column {
				Text("Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold)
				Text(
					"Manage your account and preferences", color = MaterialTheme.colorScheme.onSurfaceVariant
				)
			}
			Button(onClick = { /* Edit profile */ }) {
				Icon(Icons.Default.Edit, contentDescription = null)
				Spacer(Modifier.width(4.dp))
				Text("Edit Profile")
			}
		}
		
		// Profile Header
		Card(
			shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()
		) {
			Row(
				modifier = Modifier
					.padding(16.dp)
					.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically
			) {
				Box {
					// Avatar Placeholder
					Image(
						painter = painterResource(id = R.drawable.placeholder),
						contentDescription = "Avatar",
						modifier = Modifier
							.size(96.dp)
							.clip(CircleShape),
						contentScale = ContentScale.Crop
					)
					IconButton(
						onClick = { /* Change avatar */ },
						modifier = Modifier
							.size(32.dp)
							.align(Alignment.BottomEnd)
							.background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
					) {
						Icon(Icons.Default.CameraAlt, contentDescription = null)
					}
				}
				
				Spacer(Modifier.width(16.dp))
				
				Column(
					verticalArrangement = Arrangement.Center
				) {
					Text(fullName, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
					Text("Mathematics Teacher", color = MaterialTheme.colorScheme.onSurfaceVariant)
					Row(
						horizontalArrangement = Arrangement.spacedBy(8.dp),
						modifier = Modifier.padding(top = 8.dp)
					) {
						teacherData.value.subjects.forEach { subject ->
							AssistChip(onClick = { /* Filter by subject */ }, label = { Text(subject) })
						}
					}
				}
			}
		}
		
		// Personal Information
		Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
			Column(
				modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				Text("Personal Information", fontWeight = FontWeight.SemiBold)
				OutlinedTextField(
					value = fullName,
					onValueChange = { fullName = it },
					label = { Text("Full Name") })
				OutlinedTextField(
					value = email,
					onValueChange = { email = it },
					label = { Text("Email Address") })
				OutlinedTextField(
					value = phone,
					onValueChange = { phone = it },
					label = { Text("Phone Number") })
				OutlinedTextField(
					value = experience,
					onValueChange = { experience = it },
					label = { Text("Teaching Experience") })
				OutlinedTextField(
					value = address,
					onValueChange = { address = it },
					label = { Text("Address") })
				OutlinedTextField(
					value = education,
					onValueChange = { education = it },
					label = { Text("Education") })
			}
		}
		
		// Professional Details
		Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
			Column(
				modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
			) {
				Text("Professional Details", fontWeight = FontWeight.SemiBold)
				Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
					) {
						Icon(Icons.Default.CalendarToday, contentDescription = null)
						Text("Join Date")
						Text(teacherData.value.joinDate, color = MaterialTheme.colorScheme.onSurfaceVariant)
					}
					Column(
						horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
					) {
						Icon(Icons.Default.School, contentDescription = null)
						Text("Experience")
						Text(teacherData.value.experience, color = MaterialTheme.colorScheme.onSurfaceVariant)
					}
				}
				OutlinedTextField(
					value = bio,
					onValueChange = { bio = it },
					label = { Text("Bio / About Me") },
					placeholder = { Text("Tell us about yourself, your teaching philosophy, and interests...") },
					modifier = Modifier.fillMaxWidth(),
					maxLines = 5
				)
			}
		}
		
		// App Settings
		Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
			Column(
				modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
			) {
				Text("App Settings", fontWeight = FontWeight.SemiBold)
				SettingItem(
					"Push Notifications", "Receive notifications for important updates", pushNotifications
				) {
					pushNotifications = it
				}
				HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
				SettingItem(
					"Email Notifications", "Get email updates about attendance and marks", emailNotifications
				) {
					emailNotifications = it
				}
				HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
				SettingItem("Dark Mode", "Switch between light and dark themes", darkMode) {
					darkMode = it
				}
				HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
				SettingItem(
					"Auto-save Attendance", "Automatically save attendance entries", autoSaveAttendance
				) {
					autoSaveAttendance = it
				}
			}
		}
		
		// Action Buttons
		Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
			Button(modifier = Modifier.weight(1f), onClick = { /* Save */ }) { Text("Save Changes") }
			OutlinedButton(
				modifier = Modifier.weight(1f), onClick = { /* Reset */ }) { Text("Reset to Default") }
			Button(
				modifier = Modifier.weight(1f),
				colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
				onClick = { /* Delete */ }) {
				Text("Delete Account", color = MaterialTheme.colorScheme.onError)
			}
		}
	}
}


