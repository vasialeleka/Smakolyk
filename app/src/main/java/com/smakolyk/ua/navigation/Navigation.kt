package com.smakolyk.ua.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smakolyk.ua.components.cart.presentation.CardScreen
import com.smakolyk.ua.components.category.presentation.CategoryScreen
import com.smakolyk.ua.components.details.presentation.DetailScreen
import com.smakolyk.ua.components.home.presentation.HomeScreen
import com.smakolyk.ua.components.splash.presentation.SplashScreen

@Composable
fun Navigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navigationController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navigationController)
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navigationController)
        }

        composable(route = Screen.Details.route) {
            DetailScreen(navController = navigationController)
        }

        composable(route = Screen.Card.route) {
            CardScreen(navigationController)
        }
    }
}
