package com.example.githubclient.ui.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.githubclient.ui.data.models.GitHubRepo

@Composable
fun SearchScreen(viewModel: GitHubViewModel, navController: NavController) {
    var username by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Enter GitHub Username") }
        )
        Button(onClick = { viewModel.searchRepos(username) }) {
            Text("Search")
        }

        LazyColumn {
            items(viewModel.repos) { repo ->
                RepoItem(repo, navController, viewModel)
            }
        }
    }
}

@Composable
fun RepoItem(repo: GitHubRepo, navController: NavController, viewModel: GitHubViewModel) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(Modifier.clickable {
            navController.navigate("repo_detail/${repo.name}")
        }) {
            Text(repo.name)
            TextButton(onClick = {
                val url = "https://github.com/${repo.owner.login}/${repo.name}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }) {
                Text("Open in Browser")
            }
            Button(onClick = { viewModel.downloadRepo(context, repo) }) {
                Text("Download")
            }
        }
    }
}