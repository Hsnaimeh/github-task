package com.hisham.githubviewer.feature.user.data.repository

import com.hisham.githubviewer.feature.user.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {
     fun searchContents(query: String): Flow<SearchResult>
}