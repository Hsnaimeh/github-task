package com.hisham.githubviewer.feature.user.data.remote

import com.hisham.githubviewer.feature.user.data.model.UserProfileRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRemoteService {

    @GET("users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): UserProfileRemote
}
