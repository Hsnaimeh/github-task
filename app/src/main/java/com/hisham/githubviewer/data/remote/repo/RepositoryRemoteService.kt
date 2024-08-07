package com.hisham.githubviewer.data.remote.repo

import com.hisham.githubviewer.data.model.RepositoryRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryRemoteService {

    @GET("users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") userId: String): List<RepositoryRemote>
}
