package com.hisham.githubviewer.feature.user.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.githubviewer.di.IoDispatcher
import com.hisham.githubviewer.feature.user.domain.usecase.GetUserSearchUseCase
import com.hisham.githubviewer.feature.repo.domain.model.RepoDetails
import com.hisham.githubviewer.feature.repo.domain.model.Repository
import com.hisham.githubviewer.feature.user.domain.usecase.SearchQueryUseCase
import com.hisham.githubviewer.feature.user.presentation.ui.SearchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus
import javax.inject.Inject
@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val searchQueryUseCase: SearchQueryUseCase,
    private val repoDetailsFlow: MutableStateFlow<RepoDetails?>,
    private val savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    companion object {
        private const val SEARCH_QUERY = "search_query"
    }

    val searchQuery: StateFlow<String> = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    val searchResultUiState: StateFlow<SearchResultUiState> = searchQuery
        .drop(1)
        .flatMapLatest(searchQueryUseCase::invoke)
        .stateIn(
            scope = viewModelScope + dispatcher,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchResultUiState.EmptyQuery()
        )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onRepoSelected(repo: Repository) {
        val totalForks = (searchResultUiState.value as? SearchResultUiState.Success)?.totalForks ?: 0
        repoDetailsFlow.value = RepoDetails(repo, totalForks)
    }
}
