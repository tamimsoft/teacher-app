package com.mycampus.teacher.app.features.attendance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
	@Query("SELECT * FROM attendance WHERE studentId = :studentId")
	fun getAttendanceForStudent(studentId: Int): Flow<List<AttendanceEntity>>
	
	@Query("SELECT * FROM attendance WHERE synced = 0")
	suspend fun getUnsyncedAttendance(): List<AttendanceEntity>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertAttendance(attendance: AttendanceEntity)
	
	@Update
	suspend fun updateAttendance(attendance: AttendanceEntity)
}