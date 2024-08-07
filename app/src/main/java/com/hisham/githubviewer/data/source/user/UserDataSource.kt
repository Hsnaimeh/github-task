package com.hisham.githubviewer.data.source.user

import com.hisham.githubviewer.data.model.RepositoryProfileRemote
import com.hisham.githubviewer.data.remote.user.UserRemoteService
import javax.inject.Inject
import javax.inject.Singleton

interface IUserDataSource {
    suspend fun getUserProfile(userId: String): RepositoryProfileRemote
}

@Singleton
class UserDataSource @Inject constructor(
    private val service: UserRemoteService
) : IUserDataSource {

    override suspend fun getUserProfile(userId: String): RepositoryProfileRemote =
        service.getUserProfile(userId)
}
