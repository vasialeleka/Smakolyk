package com.smakolyk.ua.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(route = "Splash_screen")
    object Home : Screen(route = "Home_screen")

    object Category : Screen(route = "Category_screen")

    object Details : Screen(route = "Details_screen")

    object Card : Screen(route = "Card_screen")
}
