package com.example.githubclient.ui.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavigation(viewModel: GitHubViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("search") { SearchScreen(viewModel, navController) }
        composable("downloads") { DownloadsScreen(viewModel) }
    }
}