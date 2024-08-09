package com.hisham.githubviewer.di

import com.hisham.githubviewer.feature.repo.data.mapper.RepositoryMapper
import com.hisham.githubviewer.feature.user.data.mapper.UserProfileMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun provideUserProfileMapper(): UserProfileMapper {
        return UserProfileMapper()
    }

    @Provides
    @Singleton
    fun provideRepositoryMapper(): RepositoryMapper {
        return RepositoryMapper()
    }

}