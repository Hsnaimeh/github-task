package com.hisham.githubviewer.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hisham.githubviewer.app.ui.theme.HishamGithubViewerTheme
import com.hisham.githubviewer.core.navigation.Route
import com.hisham.githubviewer.core.ui.GithubAppScaffold
import com.hisham.githubviewer.core.ui.ScreenNavParams
import com.hisham.githubviewer.feature.repo.presentation.viewmodel.RepoDetailsViewModel
import com.hisham.githubviewer.feature.user.presentation.ui.HomeScreen
import com.hisham.githubviewer.feature.user.presentation.viewmodel.UserSearchViewModel
import com.hisham.githubviewer.feature.repo.presentation.ui.RepoDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            HishamGithubViewerTheme {
                GitHubApp(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                )
            }
        }
    }
}
@Composable
fun GitHubApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    GithubAppHost(
        modifier = modifier,
        startDestination = Route.Home,
        navController = navController,
    )
}


@Composable
fun GithubAppHost(
    modifier: Modifier = Modifier,
    startDestination: Route = Route.Home,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        scaffoldedComposable<Route.Home>(ScreenNavParams.NoBack(navController)) { innerPadding ->
            val searchViewModel: UserSearchViewModel = hiltViewModel()
            HomeScreen(searchViewModel, { navController.navigate(Route.RepoDetails) }, innerPadding)
        }

        scaffoldedComposable<Route.RepoDetails>(ScreenNavParams.BackEnabled(navController) { navController.navigateUp() }) { innerPadding ->
            val repoDetailsViewModel: RepoDetailsViewModel = hiltViewModel()
            RepoDetailScreen(repoDetailsViewModel, innerPadding)
        }
    }
}

inline fun <reified T : Route> NavGraphBuilder.scaffoldedComposable(
    screenNavParams: ScreenNavParams,
    crossinline content: @Composable (innerPadding: PaddingValues) -> Unit,
) {
    composable<T>(enterTransition = {
        return@composable fadeIn(tween(1000))
    }) {
        GithubAppScaffold(screenNavParams) { innerPadding ->
            content(innerPadding)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HishamGithubViewerTheme {
    }
}