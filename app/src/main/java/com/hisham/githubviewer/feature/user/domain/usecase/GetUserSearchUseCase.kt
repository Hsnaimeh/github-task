package com.hisham.githubviewer.feature.user.domain.usecase

import com.hisham.githubviewer.feature.user.data.repository.UserRepository
import javax.inject.Inject

class GetUserSearchUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(query: String) = userRepository.searchContents(query)
}