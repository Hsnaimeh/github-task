package com.hisham.githubviewer.feature.repo.domain.repository

import com.hisham.githubviewer.feature.repo.data.mapper.RepositoryMapper
import com.hisham.githubviewer.feature.repo.data.repository.RepoRepository
import com.hisham.githubviewer.feature.repo.data.source.IRepositoryDataSource
import com.hisham.githubviewer.feature.repo.domain.model.Repository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepoRepositoryImpl @Inject constructor(
    private val repositoryDataSource: IRepositoryDataSource,
    private val repositoryMapper: RepositoryMapper
) : RepoRepository {
    override suspend fun getUserRepos(userId: String): List<Repository> {
        val dtoList = repositoryDataSource.getUserRepos(userId)
        return repositoryMapper.mapList(dtoList)
    }
}