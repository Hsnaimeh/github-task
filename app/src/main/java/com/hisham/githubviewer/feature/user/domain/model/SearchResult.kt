package com.hisham.githubviewer.feature.user.domain.model

import com.hisham.githubviewer.feature.repo.domain.model.Repository

data class SearchResult(
    val repoList: List<Repository> = emptyList(),
    val username: String = "",
    val avatarUrl: String = "",
    val totalForks: Long = 0L

)
