package com.example.connektions.navigation

/**
 * Represents different screens in the application with their associated routes for navigation.
 */

sealed class Screen(val route: String) {
    object ProfileScreen : Screen("profile_screen")

}
