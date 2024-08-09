package com.hisham.githubviewer.feature.repo.data.repository

import com.hisham.githubviewer.feature.repo.domain.model.Repository

interface RepoRepository {
    suspend fun getUserRepos(userId: String): List<Repository>
}