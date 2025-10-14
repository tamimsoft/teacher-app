package com.mycampus.teacher.app.core.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
	name = "my_secure_prefs"
)

@Singleton
class SecureStore @Inject constructor(@param:ApplicationContext private val context: Context) {
	object Keys {
		val ACCESS_TOKEN = stringPreferencesKey("access_token")
		val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
	}
	val accessToken: Flow<String?> = context.dataStore.data.map { prefs ->
		prefs[Keys.ACCESS_TOKEN]
	}
	
	suspend fun saveAccessToken(token: String) {
		context.dataStore.edit { prefs ->
			prefs[Keys.ACCESS_TOKEN] = token
		}
	}
	
	val refreshToken: Flow<String?> = context.dataStore.data.map { prefs ->
		prefs[Keys.REFRESH_TOKEN]
	}
	
	suspend fun saveRefreshToken(token: String) {
		context.dataStore.edit { prefs ->
			prefs[Keys.REFRESH_TOKEN] = token
		}
	}
	
	suspend fun clear() {
		context.dataStore.edit { it.clear() }
	}
}
