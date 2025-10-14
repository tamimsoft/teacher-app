package com.mycampus.teacher.app.features.questionBank.domain.model

data class Question(
	val id: String,
	val question: String,
	val type: String,
	val subject: String,
	val topic: String,
	val difficulty: String,
	val options: List<String>? = null,
	val correctAnswer: String,
	val explanation: String? = null
)