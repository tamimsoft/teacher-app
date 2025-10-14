package com.mycampus.teacher.app.config.di

import com.mycampus.teacher.app.features.auth.data.remote.AuthApi
import com.mycampus.teacher.app.features.auth.data.remote.AuthRepositoryImpl
import com.mycampus.teacher.app.features.auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): AuthRepository {
        return AuthRepositoryImpl(api)
    }
  
//    @Provides
//    @Singleton
//    fun provideUserRepository(api: UserApi): UserRepository {
//        return UserRepositoryImpl(api)
//    }
//
//    @Provides
//    @Singleton
//    fun provideProductRepository(api: ProductApi): ProductRepository {
//        return ProductRepositoryImpl(api)
//    }
}