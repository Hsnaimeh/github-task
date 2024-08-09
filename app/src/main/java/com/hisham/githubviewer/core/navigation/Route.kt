package com.hisham.githubviewer.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {

    @Serializable
    data object Home : Route()

    @Serializable
    data object RepoDetails : Route()
}
