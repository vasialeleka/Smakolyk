package com.smakolyk.ua.components.splash.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SplashVm @Inject constructor() : ViewModel() {
    init {
        lunchTimer()
    }

    private fun lunchTimer() = runBlocking {
        launch {
            delay(SPLASH_DELAY)
            navigateNext()
        }
    }

    private fun navigateNext() {
        Log.d("Networkk", "5 seconds")
    }

    companion object {
        private const val SPLASH_DELAY = 1500L
    }
}
