package com.example.connektions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.connektions.navigation.AppNavigationGraph
import com.example.connektions.ui.theme.AppTheme
import com.example.connektions.ui.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<ProfileViewModel>()
            val navController = rememberNavController()
            AppTheme {
                AppNavigationGraph(navController, viewModel)
            }
        }
    }
}



