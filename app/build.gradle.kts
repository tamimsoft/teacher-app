@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	
	// Hilt dependencies injection
	alias(libs.plugins.hilt.android)
	// ksp
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.mycampus.teacher"
	compileSdk = 36
	
	defaultConfig {
		applicationId = "com.mycampus.teacher"
		minSdk = 27
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	val configProperties = Properties().apply {
		load(rootProject.file("app/config.properties").reader())
	}
	
	buildTypes {
		debug {
			isDebuggable = true
			isMinifyEnabled = false
			isShrinkResources = false
			applicationIdSuffix = ".debug"
			versionNameSuffix = "-debug"
			
			buildConfigField("String", "HOST", "\"${configProperties["debug.host"]}\"")
			buildConfigField("String", "BASE_URL", "\"${configProperties["debug.base_url"]}\"")
			buildConfigField("String", "API_KEY", "\"${configProperties["debug.api_key"]}\"")
			buildConfigField(
				"boolean", "ENABLE_LOGGING", "${configProperties["debug.enable_logging"]}"
			)
			buildConfigField(
				"long", "TIMEOUT_SECONDS", "${configProperties["debug.timeout_seconds"]}"
			)
			buildConfigField(
				"String", "BUILD_TYPE", "\"${configProperties["debug.build_type"]}\""
			)
		}
		create("staging") {
			isDebuggable = true
			isMinifyEnabled = true
			isShrinkResources = true
			applicationIdSuffix = ".staging"
			versionNameSuffix = "-staging"
			
			buildConfigField("String", "HOST", "\"${configProperties["staging.host"]}\"")
			buildConfigField("String", "BASE_URL", "\"${configProperties["staging.base_url"]}\"")
			buildConfigField("String", "API_KEY", "\"${configProperties["staging.api_key"]}\"")
			buildConfigField(
				"boolean", "ENABLE_LOGGING", "${configProperties["staging.enable_logging"]}"
			)
			buildConfigField(
				"long", "TIMEOUT_SECONDS", "${configProperties["staging.timeout_seconds"]}"
			)
			buildConfigField(
				"String", "BUILD_TYPE", "\"${configProperties["staging.build_type"]}\""
			)
		}
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
			)
			
			buildConfigField("String", "HOST", "\"${configProperties["release.host"]}\"")
			buildConfigField("String", "BASE_URL", "\"${configProperties["release.base_url"]}\"")
			buildConfigField("String", "API_KEY", "\"${configProperties["release.api_key"]}\"")
			buildConfigField(
				"boolean", "ENABLE_LOGGING", "${configProperties["release.enable_logging"]}"
			)
			buildConfigField(
				"long", "TIMEOUT_SECONDS", "${configProperties["release.timeout_seconds"]}"
			)
			buildConfigField(
				"String", "BUILD_TYPE", "\"${configProperties["release.build_type"]}\""
			)
		}
	}
	
	
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		buildConfig = true    // Enable custom BuildConfig fields
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.7.0"
	}
}

dependencies {
	
	// Encrypted DataStore → for small, sensitive key-value data like tokens, passwords, session info.
	implementation(libs.androidx.datastore.preferences)
	implementation(libs.androidx.datastore.preferences.core)
	//implementation("androidx.datastore:datastore-preferences-encryption:1.0.0-alpha05")
	
	// Room → for structured, relational data like user profiles, schools, classes, transactions, etc.
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)
	
	//- Foundation library for TextFieldState
	implementation(libs.androidx.foundation)
	
	// Dependency injection utils
	implementation(libs.hilt.android)
	implementation(libs.hilt.android)
	ksp(libs.hilt.compiler)
	implementation(libs.androidx.hilt.navigation.compose) // to inject ViewModels
	implementation(libs.androidx.lifecycle.viewmodel.compose) // Correct for ViewModel in Compose
	
	
	// Network call utils
	implementation(libs.retrofit)
	implementation(libs.converter.gson)
	implementation(libs.logging.interceptor) // OkHttp Logging Interceptor
	
	// Ui
	implementation(libs.androidx.appcompat)
	implementation(libs.coil.compose) // Coroutine Image Loader.
	
	
	// Default
	implementation(platform(libs.androidx.compose.bom))
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material.icons.extended)
	implementation(libs.material3)
	
	// Test dependencies
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}