package com.hisham.githubviewer.di.remote

import com.hisham.githubviewer.feature.repo.data.source.IRepositoryDataSource
import com.hisham.githubviewer.feature.repo.data.source.RepositoryDataSource
import com.hisham.githubviewer.feature.user.data.source.IUserDataSource
import com.hisham.githubviewer.feature.user.data.source.UserDataSource
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
