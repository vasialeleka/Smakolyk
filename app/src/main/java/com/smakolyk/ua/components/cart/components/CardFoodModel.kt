package com.smakolyk.ua.components.cart.components

data class CardFoodModel(
    val imageRes: Int,
    val name: String,
    var amount: Int,
    var price: Int,
    val size: String,
    val currency: String = "$"
)
