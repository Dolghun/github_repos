package com.example.githubclient.ui.domain

import com.example.githubclient.ui.data.DownloadedRepo
import com.example.githubclient.ui.data.models.GitHubRepo

interface GitHubRepository {
    suspend fun getUserRepos(username: String): List<GitHubRepo>
    suspend fun insertDownloadedRepo(repo: DownloadedRepo)
    suspend fun getDownloadedRepos(): List<DownloadedRepo>
}