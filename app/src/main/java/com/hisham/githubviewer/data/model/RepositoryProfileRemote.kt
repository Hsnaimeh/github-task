package com.hisham.githubviewer.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryProfileRemote(
    @SerialName("name")
    val name: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)
