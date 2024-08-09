package com.hisham.githubviewer.feature.user.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hisham.githubviewer.R
import com.hisham.githubviewer.app.ui.theme.AccentPink
import com.hisham.githubviewer.app.ui.theme.NeutralGray
import com.hisham.githubviewer.feature.repo.domain.model.Repository

@Composable
fun UserSearchComponent(
    searchQuery: String,
    searchResultUiState: SearchResultUiState,
    onSearchQueryChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
    onResultDisplaySelectAction: (Repository) -> Unit = {},
    searchResultWidget: @Composable (SearchResultUiState, widgetAction: (Repository) -> Unit) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            TextField(
                modifier = Modifier.weight(9f).testTag("searchTextField"),
                value = searchQuery,
                onValueChange = onSearchQueryChanged,
                label = { Text(stringResource(R.string.search_label)) },
                colors = TextFieldDefaults.colors(
                    focusedLabelColor = AccentPink,
                    unfocusedLabelColor = NeutralGray,
                    focusedIndicatorColor = AccentPink,
                    unfocusedIndicatorColor = NeutralGray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                )
            )

            Spacer(modifier = Modifier.weight(0.5f))

            ElevatedButton(
                onClick = {
                    onSearchButtonClicked()
                    keyboardController?.hide()
                },
                shape = RoundedCornerShape(CornerSize(dimensionResource(id = R.dimen.corner_size_small))),
                modifier = Modifier.weight(4f).height(dimensionResource(id = R.dimen.button_height)).testTag("searchButton"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeutralGray,
                )
            ) {
                Text(
                    stringResource(R.string.search_button_label),
                    color = Color.Black,
                    fontSize = 16.sp,
                )
            }
        }

        searchResultWidget(searchResultUiState, onResultDisplaySelectAction)
    }
}

sealed interface SearchResultUiState {
    data object Loading : SearchResultUiState
    data class EmptyQuery(val message: String = "Please enter a search query") : SearchResultUiState

    data object LoadFailed : SearchResultUiState

    data class Success(
        val avatarUrl: String = "",
        val username: String = "",
        val repoList: List<Repository> = emptyList(),
        val totalForks: Long = 0L
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = avatarUrl.isBlank() && repoList.isEmpty() && username.isBlank()
    }
}

@Preview(showBackground = true)
@Composable
fun UserSearchPreview() {
    UserSearchComponent(
        searchQuery = "Hello",
        searchResultUiState = SearchResultUiState.Success(
            avatarUrl = "https://example.com/avatar.png",
            repoList = listOf(
                Repository(
                    "Repo 1",
                    "Description 1",
                    "2022-01-01",
                    100,
                    50,
                ),
                Repository(
                    "Repo 2",
                    "Description 2",
                    "2022-01-02",
                    200,
                    60,
                ),
            ),
        ),
        onSearchQueryChanged = {},
        onSearchButtonClicked = {},
        onResultDisplaySelectAction = { },
    ) { _, _ ->
        Box {
            Text("Search Result")
        }
    }
}
