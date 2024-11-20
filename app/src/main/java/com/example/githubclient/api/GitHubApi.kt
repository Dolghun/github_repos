package com.example.githubclient.api

import com.example.githubclient.ui.data.models.GitHubRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{user}/repos")
    suspend fun getUserRepos(@Path("user") username: String): List<GitHubRepo>
}