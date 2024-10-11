package com.example.githubclient.ui.data.models

data class GitHubRepo(
    val id: Long,
    val owner: Owner,
    val name: String,
    val html_url: String,
    val clone_url: String
)
