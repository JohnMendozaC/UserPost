package com.lupesoft.pruebadeingreso.di

import android.content.Context
import com.lupesoft.pruebadeingreso.data.AppDataBase
import com.lupesoft.pruebadeingreso.data.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
        AppDataBase.getInstance(context)

    @Provides
    fun provideUserDao(appDataBase: AppDataBase): UserDao = appDataBase.userDao()

}