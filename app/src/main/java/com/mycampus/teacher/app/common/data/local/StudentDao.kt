package com.mycampus.teacher.app.common.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
	@Query("SELECT * FROM students ORDER BY name ASC")
	fun getAllStudents(): Flow<List<StudentEntity>>
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertStudents(students: List<StudentEntity>)
}
