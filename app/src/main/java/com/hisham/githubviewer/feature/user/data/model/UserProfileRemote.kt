package com.hisham.githubviewer.feature.user.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileRemote(
    @SerialName("name")
    val name: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)
