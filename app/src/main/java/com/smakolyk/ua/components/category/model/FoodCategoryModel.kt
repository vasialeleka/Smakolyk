package com.smakolyk.ua.components.category.model

import androidx.annotation.DrawableRes
import com.smakolyk.ua.components.details.model.IngredientModel

data class FoodCategoryModel(
    @DrawableRes val imageRes: Int,
    val title: String,
    val restaurant: String,
    val price: String,
    val description: String,
    val ingredients: List<IngredientModel> = emptyList(),
    val rating: String = "",
    val delivery: String = "",
    val deliveryPrice: String = "",
    val amount: Int = 0,
    val totalPrice: Int = 0
)
