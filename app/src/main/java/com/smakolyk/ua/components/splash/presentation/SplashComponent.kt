package com.smakolyk.ua.components.splash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smakolyk.ua.components.splash.vm.SplashVm
import com.smakolyk.ua.ui.theme.Main200

@Composable
fun SplashComponent(navController: NavController, vm: SplashVm = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Main200),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Smakolyk", style = MaterialTheme.typography.h3)
    }
}
