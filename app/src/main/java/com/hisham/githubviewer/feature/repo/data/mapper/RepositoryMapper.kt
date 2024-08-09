package com.hisham.githubviewer.feature.repo.data.mapper

import com.hisham.githubviewer.core.mapper.Mapper
import com.hisham.githubviewer.feature.repo.data.model.RepositoryRemote
import com.hisham.githubviewer.feature.repo.domain.model.Repository
import javax.inject.Inject

class RepositoryMapper @Inject constructor()  : Mapper<RepositoryRemote, Repository>() {
    override fun map(source: RepositoryRemote): Repository {
        return Repository(
            name = source.name,
            description = source.description,
            updatedAt = source.updatedAt,
            stargazersCount = source.stargazersCount,
            forks = source.forks
        )
    }
}
