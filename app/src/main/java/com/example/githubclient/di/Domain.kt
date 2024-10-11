package com.example.githubclient.di

import GitHubApi
import androidx.room.Room
import com.example.githubclient.BASE_URL
import com.example.githubclient.DATABASE_NAME
import com.example.githubclient.ui.data.AppDatabase
import com.example.githubclient.ui.domain.GitHubRepository
import com.example.githubclient.ui.domain.GitHubRepositoryImpl
import com.example.githubclient.ui.presentation.GitHubViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val domain = module {
    single { GitHubRepositoryImpl(get(), get()) as GitHubRepository }
    single { createWebService<GitHubApi>(BASE_URL) }
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }.withOptions {
        createdAtStart()
    }

    single { get<AppDatabase>().repoDao() }

    viewModel { GitHubViewModel(get()) }
}

inline fun <reified T> createWebService(url: String): T {
    val retrofit = baseRetrofitBuilder(url)
        .build()
    return retrofit.create(T::class.java)
}

fun baseRetrofitBuilder(url: String): Retrofit.Builder {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
}