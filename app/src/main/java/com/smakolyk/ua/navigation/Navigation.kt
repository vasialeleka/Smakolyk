package com.smakolyk.ua.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smakolyk.ua.components.home.presentation.HomeComponent
import com.smakolyk.ua.components.splash.presentation.SplashComponent

@Composable
fun Navigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Screen.Home.route) {
        composable(route = Screen.Splash.route) {
            SplashComponent(navController = navigationController)
        }

        composable(route = Screen.Home.route) {
            HomeComponent(navController = navigationController)
        }
    }
}
