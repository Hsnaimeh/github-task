package com.hisham.githubviewer.feature.user.domain.usecase

import com.hisham.githubviewer.feature.repo.domain.model.Repository
import javax.inject.Inject

class CalculateTotalForksUseCase @Inject constructor() {
    fun execute(repoList: List<Repository>): Long {
        return repoList.sumOf { it.forks }
    }
}