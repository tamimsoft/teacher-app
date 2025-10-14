package com.mycampus.teacher.app.features.questionBank.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mycampus.teacher.app.features.questionBank.domain.model.Question
import com.mycampus.teacher.app.features.questionBank.ui.component.AnalyticsCard
import com.mycampus.teacher.app.features.questionBank.ui.component.DropdownMenuBox


@SuppressLint("MutableCollectionMutableState")
@Composable
fun QuestionBankScreen() {
	var searchTerm by remember { mutableStateOf("") }
	var selectedSubject by remember { mutableStateOf("All") }
	var selectedDifficulty by remember { mutableStateOf("All") }
	var selectedType by remember { mutableStateOf("All") }
	var isAddDialogOpen by remember { mutableStateOf(false) }
	
	var questions by remember {
		mutableStateOf(
			mutableListOf(
				Question(
					id = "1",
					question = "What is the value of x in 2x + 5 = 15?",
					type = "multiple-choice",
					subject = "Algebra",
					topic = "Linear Equations",
					difficulty = "easy",
					options = listOf("5", "10", "15", "20"),
					correctAnswer = "5",
					explanation = "2x = 10, x = 5"
				), Question(
					id = "2",
					question = "The sum of angles in a triangle is always 180 degrees.",
					type = "true-false",
					subject = "Geometry",
					topic = "Triangle Properties",
					difficulty = "easy",
					correctAnswer = "true"
				)
			)
		)
	}
	
	val subjects = listOf("All", "Algebra", "Geometry", "Statistics", "Calculus")
	val difficulties = listOf("All", "Easy", "Medium", "Hard")
	val questionTypes = listOf("All", "Multiple Choice", "True/False", "Short Answer", "Essay")
	
	var selectedTab by remember { mutableStateOf("questions") }
	
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp)
	) {
		
		// Header
		Column {
			Text(
				"Question Bank",
				fontSize = 24.sp,
				fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
			)
			Text("Create and manage your collection of exam questions", color = Color.Gray)
		}
		
		// Tabs
		TabRow(selectedTabIndex = if (selectedTab == "questions") 0 else 1) {
			Tab(selected = selectedTab == "questions", onClick = { selectedTab = "questions" }) {
				Text("Question Library", modifier = Modifier.padding(16.dp))
			}
			Tab(selected = selectedTab == "analytics", onClick = { selectedTab = "analytics" }) {
				Text("Analytics", modifier = Modifier.padding(16.dp))
			}
		}
		
		if (selectedTab == "questions") {
			Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
				// Search & Filters
				Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
					Column(
						modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
					) {
						TextField(
							value = searchTerm,
							onValueChange = { searchTerm = it },
							placeholder = { Text("Search questions or topics...") },
							leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
							singleLine = true,
							modifier = Modifier.fillMaxWidth()
						)
						Row(
							modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
						) {
							DropdownMenuBox(
								selectedItem = selectedSubject,
								options = subjects,
								onItemSelected = { selectedSubject = it })
							DropdownMenuBox(
								selectedItem = selectedDifficulty,
								options = difficulties,
								onItemSelected = { selectedDifficulty = it })
							DropdownMenuBox(
								selectedItem = selectedType,
								options = questionTypes,
								onItemSelected = { selectedType = it })
							Button(onClick = { isAddDialogOpen = true }) {
								Icon(
									Icons.Default.Add, contentDescription = null
								); Spacer(Modifier.width(4.dp)); Text("Add Question")
							}
						}
					}
				}
				
				// Question List
				val filteredQuestions = questions.filter {
					(it.question.contains(searchTerm, true) || it.topic.contains(
						searchTerm, true
					)) && (selectedSubject == "All" || it.subject.equals(
						selectedSubject, true
					)) && (selectedDifficulty == "All" || it.difficulty.equals(
						selectedDifficulty, true
					)) && (selectedType == "All" || it.type.replace("-", " ").equals(selectedType, true))
				}
				
				if (filteredQuestions.isEmpty()) {
					Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
						Column(
							horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)
						) {
							Icon(
								Icons.AutoMirrored.Filled.Help,
								contentDescription = null,
								modifier = Modifier.size(48.dp),
								tint = Color.Gray
							)
							Text("No Questions Found", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
							Text(
								"Try adjusting your search or filters, or add a new question.", color = Color.Gray
							)
						}
					}
				} else {
					LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
						items(filteredQuestions.size) { index ->
							val question = filteredQuestions[index]
							Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
								Column(modifier = Modifier.padding(12.dp)) {
									Row(
										verticalAlignment = Alignment.CenterVertically,
										horizontalArrangement = Arrangement.SpaceBetween,
										modifier = Modifier.fillMaxWidth()
									) {
										Column {
											Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
												Text(question.type.uppercase(), fontSize = 12.sp, color = Color.Blue)
												Text(question.difficulty.uppercase(), fontSize = 12.sp, color = Color.Red)
												Text(question.subject, fontSize = 12.sp, color = Color.Gray)
											}
											Text(
												question.question,
												fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
											)
											Text("Topic: ${question.topic}", fontSize = 12.sp, color = Color.Gray)
											question.options?.let {
												Text("Options: ${it.joinToString()}", fontSize = 12.sp, color = Color.Gray)
											}
											Text(
												"Correct Answer: ${question.correctAnswer}",
												fontSize = 12.sp,
												color = Color.Gray
											)
											question.explanation?.let {
												Text("Explanation: $it", fontSize = 12.sp, color = Color.Gray)
											}
										}
										Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
											IconButton(onClick = {
												val copy = question.copy(
													id = System.currentTimeMillis().toString(),
													question = question.question + " (Copy)"
												)
												questions = (mutableListOf(copy) + questions).toMutableList()
												
											}) { Icon(Icons.Default.ContentCopy, contentDescription = null) }
											IconButton(onClick = { /* Edit */ }) {
												Icon(
													Icons.Default.Edit, contentDescription = null
												)
											}
											IconButton(onClick = {
												questions = questions.filterNot { it.id == question.id }.toMutableList()
											}) { Icon(Icons.Default.Delete, contentDescription = null) }
										}
									}
								}
							}
						}
					}
				}
			}
		} else {
			// Analytics Tab
			Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
				Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
					AnalyticsCard(title = "Total Questions", count = questions.size)
					AnalyticsCard(title = "Easy", count = questions.count { it.difficulty == "easy" })
					AnalyticsCard(title = "Medium", count = questions.count { it.difficulty == "medium" })
					AnalyticsCard(title = "Hard", count = questions.count { it.difficulty == "hard" })
				}
				// Subject Distribution
				Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
					Column(
						modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
					) {
						Text("Subject Distribution", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
						subjects.drop(1).forEach { subject ->
							val count = questions.count { it.subject == subject }
							val percentage =
								if (questions.isNotEmpty()) (count.toFloat() / questions.size) * 100 else 0f
							Row(
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.spacedBy(8.dp)
							) {
								Text(subject, modifier = Modifier.width(80.dp))
								Box(
									modifier = Modifier
										.height(8.dp)
										.weight(1f)
										.background(Color.LightGray, RoundedCornerShape(4.dp))
								) {
									Box(
										modifier = Modifier
											.fillMaxHeight()
											.background(Color.Blue, RoundedCornerShape(4.dp))
											.width(percentage.dp)
									)
								}
								Text("$count")
							}
						}
					}
				}
			}
		}
		
		// Add Question Dialog
		if (isAddDialogOpen) {
			Dialog(onDismissRequest = { isAddDialogOpen = false }) {
				Card(
					shape = RoundedCornerShape(12.dp), modifier = Modifier
						.fillMaxWidth()
						.padding(16.dp)
				) {
					Column(
						modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
					) {
						Text(
							"Add New Question",
							fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
							fontSize = 18.sp
						)
						var newQuestionText by remember { mutableStateOf("") }
						TextField(
							value = newQuestionText,
							onValueChange = { newQuestionText = it },
							placeholder = { Text("Enter question") })
						Button(onClick = {
							val q = Question(
								id = System.currentTimeMillis().toString(),
								question = newQuestionText,
								type = "multiple-choice",
								subject = "",
								topic = "",
								difficulty = "medium",
								correctAnswer = ""
							)
							questions = (mutableListOf(q) + questions).toMutableList()
							
							isAddDialogOpen = false
						}, modifier = Modifier.fillMaxWidth()) { Text("Add Question") }
					}
				}
			}
		}
	}
}

