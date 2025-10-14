package com.mycampus.teacher.app.common.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
	@PrimaryKey val id: Int,
	val systemRoll: String,
	val name: String,
	val fatherName: String,
	val rollNo: String,
	val classId: Int,
	val groupId: Int,
	val shiftId: Int,
	val sectionId: Int,
)