package com.smakolyk.ua.components.home.state

import com.smakolyk.ua.components.home.model.CategoryModel
import com.smakolyk.ua.components.home.model.PopularFoodModel
import com.smakolyk.ua.components.home.model.RestaurantModel

data class HomeState(
    val name: String? = null,
    val binAmount: Int = 0,
    val deliveryAddress: String? = null,
    val search: String = "",
    val categories: List<CategoryModel> = emptyList(),
    val selectedCategory: CategoryModel? = null,
    val restaurants: List<RestaurantModel>? = null,
    val keyWords: List<String> = emptyList(),
    val popular: List<PopularFoodModel> = emptyList()
)
