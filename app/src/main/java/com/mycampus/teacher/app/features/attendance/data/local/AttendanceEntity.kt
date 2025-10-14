package com.mycampus.teacher.app.features.attendance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance")
data class AttendanceEntity(
	@PrimaryKey(autoGenerate = true) val id: Long = 0,
	val studentId: Int,
	val date: Long,
	val status: String,
	val synced: Boolean = false
)