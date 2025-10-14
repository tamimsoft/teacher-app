package com.mycampus.teacher.app.core.utils  // Change this to your actual package

import android.util.Log
import com.mycampus.teacher.BuildConfig  // Ensure BuildConfig is correctly imported

/**
 * Extension functions for simplified logging that work only in DEBUG mode.
 * Automatically uses the calling class name as the log tag.
 */

fun Any.logDebug(message: String) {
	if (BuildConfig.DEBUG) {
		Log.d(this::class.java.simpleName, message)
	}
}

fun Any.logInfo(message: String) {
	if (BuildConfig.DEBUG) {
		Log.i(this::class.java.simpleName, message)
	}
}

fun Any.logWarn(message: String) {
	if (BuildConfig.DEBUG) {
		Log.w(this::class.java.simpleName, message)
	}
}

fun Any.logError(message: String, throwable: Throwable? = null) {
	if (BuildConfig.DEBUG) {
		Log.e(this::class.java.simpleName, message, throwable)
	}
}
