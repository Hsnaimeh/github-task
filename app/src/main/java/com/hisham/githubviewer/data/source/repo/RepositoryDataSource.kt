package com.hisham.githubviewer.data.source.repo

import com.hisham.githubviewer.data.model.RepositoryRemote
import com.hisham.githubviewer.data.remote.repo.RepositoryRemoteService
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
