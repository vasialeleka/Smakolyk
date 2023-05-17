package com.smakolyk.ua.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(route = "Splash_screen")
    object Home : Screen(route = "Home_screen")
}
