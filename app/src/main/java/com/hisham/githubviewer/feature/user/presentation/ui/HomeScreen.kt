package com.hisham.githubviewer.feature.user.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hisham.githubviewer.R
import com.hisham.githubviewer.core.ui.ProfileAvatar
import com.hisham.githubviewer.core.ui.RepoList
import com.hisham.githubviewer.feature.repo.domain.model.Repository
import com.hisham.githubviewer.feature.user.presentation.viewmodel.UserSearchViewModel
@Composable
fun HomeScreen(
    viewModel: UserSearchViewModel,
    navigateToRepoDetails: () -> Unit,
    contentPadding: PaddingValues,
) {
    val searchQueryState = remember { mutableStateOf("") }
    val searchResultState = viewModel.searchResultUiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .padding(contentPadding)
            .fillMaxWidth(),
    ) {
        UserSearchComponent(
            searchQuery = searchQueryState.value,
            searchResultUiState = searchResultState.value,
            onSearchQueryChanged = { newValue -> searchQueryState.value = newValue },
            onSearchButtonClicked = { viewModel.onSearchQueryChanged(searchQueryState.value) },
            onResultDisplaySelectAction = { repository ->
                viewModel.onRepoSelected(repository)
                navigateToRepoDetails()
            },
        ) { uiState, action ->
            SearchResultsContainer(uiState, action)
        }
    }
}

@Composable
fun SearchResultsContainer(
    searchResultUiState: SearchResultUiState,
    onRepoClick: (Repository) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        AvatarSection(uiState = searchResultUiState)
        RepoListSection(uiState = searchResultUiState, onRepoClick = onRepoClick)
    }
}

@Composable
fun AvatarSection(uiState: SearchResultUiState) {
    AnimatedVisibility(
        visible = (uiState is SearchResultUiState.Success && uiState.avatarUrl.isNotBlank()),
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 1000),
            initialOffsetY = { it / 5 },
        ) + fadeIn(
            tween(1000),
            initialAlpha = 0.0f,
        ),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            ProfileAvatar(uiState)
        }
    }
}

@Composable
fun RepoListSection(
    uiState: SearchResultUiState,
    onRepoClick: (Repository) -> Unit,
) {
    RepoList(uiState, onRepoClick)
}

@Preview
@Composable
fun PreviewSearchResultsContainer() {
    SearchResultsContainer(
        searchResultUiState = SearchResultUiState.Success(
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
            username = "exampleuser",
            repoList = listOf(
                Repository(
                    name = "Sample Project",
                    description = "A sample project to demonstrate GitHub repository features.",
                    forks = 120,
                    updatedAt = "2023-07-10T00:00:00Z",
                    stargazersCount = 450,
                ),
                Repository(
                    name = "Demo App",
                    description = "An open-source app showcasing modern Android development practices.",
                    forks = 89,
                    updatedAt = "2023-06-15T00:00:00Z",
                    stargazersCount = 150,
                ),
                Repository(
                    name = "Data Visualization Tool",
                    description = "Tool for creating interactive data visualizations with various chart types.",
                    forks = 75,
                    updatedAt = "2023-05-20T00:00:00Z",
                    stargazersCount = 300,
                ),
            ),
        ),
        onRepoClick = {},
    )
}

