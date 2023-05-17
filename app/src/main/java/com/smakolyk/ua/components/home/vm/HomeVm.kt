package com.smakolyk.ua.components.home.vm

import androidx.lifecycle.ViewModel
import com.smakolyk.ua.components.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor() : ViewModel() {
    val state = MutableStateFlow(
        HomeState()
    )

    init {
        state.update { it.copy(name = "Asya", deliveryAddress = "Halal Lab office", binAmount = 2) }
    }
}
