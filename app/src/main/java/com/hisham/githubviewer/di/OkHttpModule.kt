package com.hisham.githubviewer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    private const val TIMEOUT_IN_MINUTES = 2L

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
            .build()
    }
}
