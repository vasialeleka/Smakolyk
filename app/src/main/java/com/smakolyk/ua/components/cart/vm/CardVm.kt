package com.smakolyk.ua.components.cart.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.smakolyk.ua.R
import com.smakolyk.ua.components.cart.components.CardFoodModel
import com.smakolyk.ua.components.cart.state.CardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CardVm @Inject constructor() : ViewModel() {

    val state = MutableStateFlow(
        CardState(
            foodModels = cardFoodModels(),
            totalPrice = 64
        )
    )

    private fun cardFoodModels() = mutableListOf(
        CardFoodModel(
            imageRes = R.drawable.ic_pizza,
            name = "Paperoni",
            amount = 1,
            price = 32,
            size = "14\""
        ),
        CardFoodModel(
            imageRes = R.drawable.ic_pizza,
            name = "Pizza Calzone European",
            amount = 1,
            price = 32,
            size = "14\""
        )
    )

    fun onPlusClicked(food: Int) {
        Log.d("Networkk", "$food")
        if (food == 0) {
            state.update {
                it.copy(
                    amount1 = state.value.amount1 + 1,
                    totalPrice = state.value.totalPrice + 32
                )
            }
        } else {
            state.update {
                it.copy(
                    amount2 = state.value.amount2 + 1,
                    totalPrice = state.value.totalPrice + 32
                )
            }
        }
    }

    fun onMinusClicked(food: Int) {
        if (food == 0) {
            if (state.value.amount1 > 1) {
                state.update {
                    it.copy(
                        amount1 = state.value.amount1 - 1,
                        totalPrice = state.value.totalPrice - 32
                    )
                }
            }
        } else {
            if (state.value.amount2 > 1) {
                state.update {
                    it.copy(
                        amount2 = state.value.amount2 - 1,
                        totalPrice = state.value.totalPrice - 32
                    )
                }
            }
        }
    }
}
