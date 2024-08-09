package com.hisham.githubviewer.feature.user.data.source

import com.hisham.githubviewer.feature.user.data.model.UserProfileRemote
import com.hisham.githubviewer.feature.user.data.remote.UserRemoteService
import javax.inject.Inject
import javax.inject.Singleton

interface IUserDataSource {
    suspend fun getUserProfile(userId: String): UserProfileRemote
}

@Singleton
class UserDataSource @Inject constructor(
    private val service: UserRemoteService
) : IUserDataSource {

    override suspend fun getUserProfile(userId: String): UserProfileRemote =
        service.getUserProfile(userId)
}
