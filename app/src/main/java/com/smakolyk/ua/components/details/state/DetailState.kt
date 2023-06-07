package com.smakolyk.ua.components.details.state

import com.smakolyk.ua.components.category.model.FoodCategoryModel

data class DetailState(
    val category: FoodCategoryModel,
    val ingredientSizes: List<String> = listOf("14\"", "16\"", "18\""),
    val selectedSize: String = ingredientSizes.first()
)
