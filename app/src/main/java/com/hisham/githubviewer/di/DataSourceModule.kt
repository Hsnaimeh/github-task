package com.hisham.githubviewer.di

import com.hisham.githubviewer.data.source.repo.IRepositoryDataSource
import com.hisham.githubviewer.data.source.repo.RepositoryDataSource
import com.hisham.githubviewer.data.source.user.IUserDataSource
import com.hisham.githubviewer.data.source.user.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRepositoryDataSource(
        repositoryDataSource: RepositoryDataSource
    ): IRepositoryDataSource

    @Binds
    @Singleton
    abstract fun bindUserDataSource(
        userDataSource: UserDataSource
    ): IUserDataSource
}
