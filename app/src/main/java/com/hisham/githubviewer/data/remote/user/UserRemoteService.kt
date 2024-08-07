package com.hisham.githubviewer.data.remote.user

import com.hisham.githubviewer.data.model.RepositoryProfileRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRemoteService {

    @GET("users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): RepositoryProfileRemote
}
