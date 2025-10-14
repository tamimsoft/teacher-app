package com.mycampus.teacher.app.config.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mycampus.teacher.BuildConfig
import com.mycampus.teacher.app.features.auth.data.remote.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
	
	@Provides
	@Singleton
	fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply {
			level = if (BuildConfig.DEBUG) {
				HttpLoggingInterceptor.Level.BODY
			} else {
				HttpLoggingInterceptor.Level.NONE
			}
		}
	}
	
	@Provides
	@Singleton
	fun provideAuthInterceptor(): Interceptor {
		return Interceptor { chain ->
			val original = chain.request()
			val requestBuilder =
				original.newBuilder().header("Authorization", "Bearer ${BuildConfig.API_KEY}")
					.header("Accept", "application/json").header("Content-Type", "application/json")
			
			chain.proceed(requestBuilder.build())
		}
	}
	
	@Provides
	@Singleton
	fun provideOkHttpClient(
		loggingInterceptor: HttpLoggingInterceptor, authInterceptor: Interceptor
	): OkHttpClient {
		return OkHttpClient.Builder().connectTimeout(BuildConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
			.readTimeout(BuildConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
			.writeTimeout(BuildConfig.TIMEOUT_SECONDS, TimeUnit.SECONDS)
			.addInterceptor(loggingInterceptor).addInterceptor(authInterceptor)
			.retryOnConnectionFailure(true).build()
	}
	
	@Provides
	@Singleton
	fun provideGson(): Gson {
		return GsonBuilder().create()
	}
	
	@Provides
	@Singleton
	fun provideRetrofit(
		okHttpClient: OkHttpClient, gson: Gson
	): Retrofit {
		return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create(gson)).build()
	}
	
	
	@Provides
	@Singleton
	fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
	
	
}
