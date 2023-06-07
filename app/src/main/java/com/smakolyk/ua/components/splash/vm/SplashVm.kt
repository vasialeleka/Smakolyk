package com.smakolyk.ua.components.splash.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashVm @Inject constructor() : ViewModel() {

    val state = MutableStateFlow(false)

     fun lunchTimer(action :() -> Unit ) = runBlocking {
        launch {
            delay(SPLASH_DELAY)
            action.invoke()
        }
    }


    companion object {
        private const val SPLASH_DELAY = 3000L
    }
}
