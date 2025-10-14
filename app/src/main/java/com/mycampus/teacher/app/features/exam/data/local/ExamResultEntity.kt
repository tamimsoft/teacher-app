package com.mycampus.teacher.app.features.exam.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exam_results")
data class ExamResultEntity(
	@PrimaryKey(autoGenerate = true) val id: Long = 0,
	val studentId: Int,
	val examId: Int,
	val marks: Float,
	val grade: String,
	val synced: Boolean = false
)