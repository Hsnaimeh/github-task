package com.hisham.githubviewer.feature.user.domain.usecase

import com.hisham.githubviewer.feature.user.domain.model.SearchResult
import com.hisham.githubviewer.feature.user.presentation.ui.SearchResultUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
class SearchQueryUseCase @Inject constructor(
    private val getUserSearchUseCase: GetUserSearchUseCase
) {

    operator fun invoke(query: String): Flow<SearchResultUiState> =
        if (query.isBlank()) {
            flowOf(SearchResultUiState.EmptyQuery("Invalid input. Query cannot be blank."))
        } else {
            getUserSearchUseCase(query)
                .map(::mapSearchResultToUiState)
                .catch { emit(SearchResultUiState.LoadFailed) }
        }

    private fun mapSearchResultToUiState(data: SearchResult): SearchResultUiState =
        if (data.username.isBlank() && data.repoList.isEmpty()) {
            SearchResultUiState.LoadFailed
        } else {
            SearchResultUiState.Success(
                avatarUrl = data.avatarUrl,
                username = data.username,
                repoList = data.repoList,
                totalForks = data.totalForks
            )
        }
}
