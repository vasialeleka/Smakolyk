package com.smakolyk.ua.components.home.model

data class RestaurantModel(
    val imageRes: Int,
    val resTitle: String,
    val rating: Double,
    val deliveryTime: Int,
    val deliveryPrice: Double? = null
)
