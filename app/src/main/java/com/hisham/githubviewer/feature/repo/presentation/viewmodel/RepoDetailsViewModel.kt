package com.hisham.githubviewer.feature.repo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hisham.githubviewer.core.ui.RepoDetailsUiState
import com.hisham.githubviewer.feature.repo.domain.model.RepoDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    repoDetailsFlow: MutableStateFlow<RepoDetails?>,
) : ViewModel() {
    val repoDetailsUiState: StateFlow<RepoDetailsUiState> = repoDetailsFlow
        .map { data ->
            if (data == null) {
                RepoDetailsUiState.Loading
            } else {
                RepoDetailsUiState.Success(
                    repoDetails = RepoDetails(
                        repo = data.repo,
                        totalForks = data.totalForks,
                    ),
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RepoDetailsUiState.Loading,
        )
}
