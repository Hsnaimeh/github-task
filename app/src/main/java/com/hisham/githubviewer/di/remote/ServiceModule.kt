package com.hisham.githubviewer.di.remote

import com.hisham.githubviewer.feature.repo.data.remote.RepositoryRemoteService
import com.hisham.githubviewer.feature.user.data.remote.UserRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideUserRemoteService(retrofit: Retrofit): UserRemoteService {
        return retrofit.create(UserRemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryRemoteService(retrofit: Retrofit): RepositoryRemoteService {
        return retrofit.create(RepositoryRemoteService::class.java)
    }

}
