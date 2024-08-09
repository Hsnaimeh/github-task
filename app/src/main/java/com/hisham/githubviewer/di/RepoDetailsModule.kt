package com.hisham.githubviewer.di

import com.hisham.githubviewer.feature.repo.domain.model.RepoDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoDetailsModule {
    @Provides
    @Singleton
    fun provideSelectedRepoFlow(): MutableStateFlow<RepoDetails?> = MutableStateFlow(null)
}
