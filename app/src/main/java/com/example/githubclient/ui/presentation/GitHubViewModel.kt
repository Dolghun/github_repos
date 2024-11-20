package com.example.githubclient.ui.presentation

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclient.ui.data.DownloadedRepo
import com.example.githubclient.ui.data.models.GitHubRepo
import com.example.githubclient.data.GitHubRepository
import kotlinx.coroutines.launch

class GitHubViewModel(
    private val repository: GitHubRepository
) : ViewModel() {

    var repos by mutableStateOf<List<GitHubRepo>>(emptyList())
        private set

    var downloadedRepos by mutableStateOf<List<DownloadedRepo>>(emptyList())
        private set

    init {
        refreshDownloadedRepos()
    }

    fun searchRepos(username: String) {
        viewModelScope.launch {
            repos = repository.getUserRepos(username)
        }
    }

    fun downloadRepo(context: Context, repo: GitHubRepo) {
        val url = "https://github.com/${repo.owner.login}/${repo.name}/archive/refs/heads/main.zip"
        val request = DownloadManager.Request(Uri.parse(url))
        request.setTitle(repo.name)
        request.setDescription("Downloading repository...")

        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            "${repo.name}.zip"
        )

        request.setMimeType("application/zip")

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

        viewModelScope.launch {
            repository.insertDownloadedRepo(
                DownloadedRepo(
                    username = repo.owner.login,
                    repoName = repo.name
                )
            )
            refreshDownloadedRepos()
        }
    }

    private fun refreshDownloadedRepos() {
        viewModelScope.launch {
            downloadedRepos = repository.getDownloadedRepos()
        }
    }
}