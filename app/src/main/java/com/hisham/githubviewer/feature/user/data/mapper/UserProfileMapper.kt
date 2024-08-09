package com.hisham.githubviewer.feature.user.data.mapper

import com.hisham.githubviewer.core.mapper.Mapper
import com.hisham.githubviewer.feature.user.data.model.UserProfileRemote
import com.hisham.githubviewer.feature.user.domain.model.UserProfile
import javax.inject.Inject

class UserProfileMapper @Inject constructor() : Mapper<UserProfileRemote, UserProfile>() {
    override fun map(source: UserProfileRemote): UserProfile {
        return UserProfile(
            name = source.name,
            avatarUrl = source.avatarUrl
        )
    }
}