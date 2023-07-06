package com.example.connektions.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.connektions.ui.screens.profile.ProfileScreen
import com.example.connektions.ui.viewmodels.ProfileViewModel

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.ProfileScreen.route
    ) {
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(viewModel = profileViewModel)
        }
    }
}