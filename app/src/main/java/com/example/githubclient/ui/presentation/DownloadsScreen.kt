package com.example.githubclient.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DownloadsScreen(viewModel: GitHubViewModel) {
    Column {
        Text("Downloaded Repositories", style = MaterialTheme.typography.bodyLarge)

        LazyColumn {
            items(viewModel.downloadedRepos) { repo ->
                Text("${repo.username} - ${repo.repoName}")
            }
        }
    }
}