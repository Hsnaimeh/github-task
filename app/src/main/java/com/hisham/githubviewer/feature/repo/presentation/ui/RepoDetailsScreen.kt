package com.hisham.githubviewer.feature.repo.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisham.githubviewer.R
import com.hisham.githubviewer.core.ui.RepoDetailsComponent
import com.hisham.githubviewer.core.ui.RepoDetailsUiState
import com.hisham.githubviewer.feature.repo.presentation.viewmodel.RepoDetailsViewModel

@Composable
fun RepoDetailScreen(repoDetailsViewModel: RepoDetailsViewModel, innerPadding: PaddingValues) {
    val repoDetailsUiState = repoDetailsViewModel.repoDetailsUiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
    ) {
        RepoDetailScreen(repoDetailsUiState.value)
    }
}

@Composable
fun RepoDetailScreen(repoDetailsUiState: RepoDetailsUiState) {
    when (repoDetailsUiState) {
        is RepoDetailsUiState.Success -> {
            RepoDetailsComponent(repoDetailsUiState)
        }
        RepoDetailsUiState.LoadFailed -> {
            Text(text = stringResource(R.string.error_loading_failed))
        }

        RepoDetailsUiState.Loading -> {
            Text(text = stringResource(R.string.repo_details_loading))
        }
    }
}
