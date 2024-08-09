package com.hisham.githubviewer.feature.repo.data.source

import com.hisham.githubviewer.feature.repo.data.model.RepositoryRemote
import com.hisham.githubviewer.feature.repo.data.remote.RepositoryRemoteService
import javax.inject.Inject
import javax.inject.Singleton

interface IRepositoryDataSource {
    suspend fun getUserRepos(userId: String): List<RepositoryRemote>
}

@Singleton
class RepositoryDataSource @Inject constructor(
    private val service: RepositoryRemoteService
) : IRepositoryDataSource {

    override suspend fun getUserRepos(userId: String): List<RepositoryRemote> =
        service.getUserRepos(userId)
}
