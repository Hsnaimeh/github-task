package com.hisham.githubviewer.feature.repo.domain.usecase

import com.hisham.githubviewer.feature.repo.data.repository.RepoRepository
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {
    suspend operator fun invoke(userId: String) = repoRepository.getUserRepos(userId)
}