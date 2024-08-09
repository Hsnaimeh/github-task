package com.hisham.githubviewer.di

import com.hisham.githubviewer.feature.repo.data.repository.RepoRepository
import com.hisham.githubviewer.feature.repo.domain.repository.RepoRepositoryImpl
import com.hisham.githubviewer.feature.user.data.repository.UserRepository
import com.hisham.githubviewer.feature.user.domain.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindRepoRepository(
        repoRepositoryImpl: RepoRepositoryImpl
    ): RepoRepository
}