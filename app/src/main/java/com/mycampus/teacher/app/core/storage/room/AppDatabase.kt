package com.mycampus.teacher.app.core.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mycampus.teacher.app.common.data.local.StudentDao
import com.mycampus.teacher.app.common.data.local.StudentEntity
import com.mycampus.teacher.app.features.attendance.data.local.AttendanceDao
import com.mycampus.teacher.app.features.attendance.data.local.AttendanceEntity
import com.mycampus.teacher.app.features.exam.data.local.ExamResultDao
import com.mycampus.teacher.app.features.exam.data.local.ExamResultEntity

@Database(
	entities = [StudentEntity::class, AttendanceEntity::class, ExamResultEntity::class],
	version = 1,
	exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun studentDao(): StudentDao
	abstract fun attendanceDao(): AttendanceDao
	abstract fun examResultDao(): ExamResultDao
}
