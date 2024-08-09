package com.hisham.githubviewer.feature.repo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryRemote(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String? = "",
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("stargazers_count")
    val stargazersCount: Int,
    @SerialName("forks")
    val forks: Long
)
