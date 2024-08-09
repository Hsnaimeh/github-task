package com.hisham.githubviewer.feature.user.domain.repository

import com.hisham.githubviewer.feature.repo.data.mapper.RepositoryMapper
import com.hisham.githubviewer.feature.repo.data.source.RepositoryDataSource
import com.hisham.githubviewer.feature.user.data.mapper.UserProfileMapper
import com.hisham.githubviewer.feature.user.data.repository.UserRepository
import com.hisham.githubviewer.feature.user.data.source.IUserDataSource
import com.hisham.githubviewer.feature.user.domain.model.SearchResult
import com.hisham.githubviewer.feature.user.domain.usecase.CalculateTotalForksUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: IUserDataSource,
    private val repositoryDataSource: RepositoryDataSource,
    private val userProfileMapper: UserProfileMapper,
    private val repositoryMapper: RepositoryMapper,
    private val calculateTotalForksUseCase: CalculateTotalForksUseCase
) : UserRepository {

    override fun searchContents(query: String): Flow<SearchResult> {
        val userProfileFlow = fetchUserProfile(query)
        val userRepoFlow = fetchUserRepos(query)

        return combineFlows(userProfileFlow, userRepoFlow)
    }

    private fun fetchUserProfile(query: String): Flow<SearchResult> {
        return flow {
            val userProfile = userDataSource.getUserProfile(query)
            emit(userProfileMapper.map(userProfile))
        }.map { userProfile ->
            SearchResult(username = userProfile.name, avatarUrl = userProfile.avatarUrl)
        }.catch { emit(SearchResult()) }
    }

    private fun fetchUserRepos(query: String): Flow<SearchResult> {
        return flow {
            val repoList = repositoryDataSource.getUserRepos(query)
            emit(repoList.map { repositoryMapper.map(it) })
        }.map { repoList ->
            val totalForks = calculateTotalForksUseCase.execute(repoList)
            SearchResult(repoList = repoList, totalForks = totalForks)
        }.catch { emit(SearchResult()) }
    }

    private fun combineFlows(
        userProfileFlow: Flow<SearchResult>,
        userRepoFlow: Flow<SearchResult>
    ): Flow<SearchResult> {
        return combine(userProfileFlow, userRepoFlow) { userProfileResult, repoListResult ->
            SearchResult(
                username = userProfileResult.username,
                avatarUrl = userProfileResult.avatarUrl,
                repoList = repoListResult.repoList,
                totalForks = repoListResult.totalForks
            )
        }
    }
}

