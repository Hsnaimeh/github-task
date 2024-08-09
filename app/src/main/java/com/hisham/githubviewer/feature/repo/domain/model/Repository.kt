package com.hisham.githubviewer.feature.repo.domain.model

data class Repository(
    val name: String,
    val description: String? = "",
    val updatedAt: String,
    val stargazersCount: Int,
    val forks: Long
)
