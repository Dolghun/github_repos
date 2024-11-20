package com.example.githubclient.data

import com.example.githubclient.api.GitHubApi
import com.example.githubclient.ui.data.DownloadedRepo
import com.example.githubclient.ui.data.RepoDao
import com.example.githubclient.ui.data.models.GitHubRepo

class GitHubRepositoryImpl(
    private val api: GitHubApi,
    private val repoDao: RepoDao
) : GitHubRepository {
    override suspend fun getUserRepos(username: String): List<GitHubRepo> {
        return api.getUserRepos(username)
    }

    override suspend fun insertDownloadedRepo(repo: DownloadedRepo) {
        repoDao.insert(repo)
    }

    override suspend fun getDownloadedRepos(): List<DownloadedRepo> {
        return repoDao.getAll()
    }
}