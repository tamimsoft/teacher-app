package com.mycampus.teacher.app.features.exam.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamResultDao {
	@Query("SELECT * FROM exam_results WHERE studentId = :studentId")
	fun getExamResultsForStudent(studentId: Int): Flow<List<ExamResultEntity>>
	
	@Query("SELECT * FROM exam_results WHERE synced = 0")
	suspend fun getUnsyncedExamResults(): List<ExamResultEntity>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertExamResult(result: ExamResultEntity)
	
	@Update
	suspend fun updateExamResult(result: ExamResultEntity)
}