package com.smakolyk.ua.components.category.state

import com.smakolyk.ua.components.category.model.FoodCategoryModel
import com.smakolyk.ua.components.home.model.RestaurantModel

data class CategoryState(
    val category: String,
    val restaurants: List<RestaurantModel> = emptyList(),
    val burgers: List<FoodCategoryModel> = emptyList()
)