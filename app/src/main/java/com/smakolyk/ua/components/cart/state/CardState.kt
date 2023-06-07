package com.smakolyk.ua.components.cart.state

import com.smakolyk.ua.components.cart.components.CardFoodModel

data class CardState(
    val deliveryAddress: String = "Cherkasy, Nuzhnya Gorova 9",
    val foodModels: MutableList<CardFoodModel>,
    val totalPrice: Int = 0,
    val amount1: Int = 1,
    val amount2: Int = 1
)
