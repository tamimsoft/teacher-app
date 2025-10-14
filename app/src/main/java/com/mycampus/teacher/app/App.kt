package com.mycampus.teacher.app

import android.app.Application
import android.util.Log
import com.mycampus.teacher.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
	
	override fun onCreate() {
		super.onCreate()
		
		// Log build information
		Log.d("BuildConfig", "App Version: ${BuildConfig.VERSION_NAME}")
		Log.d("BuildConfig", "Build Type: ${BuildConfig.BUILD_TYPE}")
		Log.d("BuildConfig", "Base URL: ${BuildConfig.BASE_URL}")
		Log.d("BuildConfig", "Logging Enabled: ${BuildConfig.ENABLE_LOGGING}")
	}
}