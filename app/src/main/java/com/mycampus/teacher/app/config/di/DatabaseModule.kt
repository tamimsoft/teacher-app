package com.mycampus.teacher.app.config.di

import android.content.Context
import androidx.room.Room
import com.mycampus.teacher.app.core.storage.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
	
	@Provides
	@Singleton
	fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
		return Room.databaseBuilder(
			context.applicationContext, AppDatabase::class.java, "mc_database"
		).build()
	}
	
	@Provides
	fun provideStudentDao(db: AppDatabase) = db.studentDao()
	
	@Provides
	fun provideAttendanceDao(db: AppDatabase) = db.attendanceDao()
	
	@Provides
	fun provideExamResultDao(db: AppDatabase) = db.examResultDao()
}
