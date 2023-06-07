package com.smakolyk.ua.components.category.vm

import androidx.lifecycle.ViewModel
import com.smakolyk.ua.R
import com.smakolyk.ua.components.category.model.FoodCategoryModel
import com.smakolyk.ua.components.category.state.CategoryState
import com.smakolyk.ua.components.home.model.RestaurantModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CategoryVm @Inject constructor() : ViewModel() {
    val state = MutableStateFlow(
        CategoryState("Burger")
    )

    init {
        val food = listOf<FoodCategoryModel>(
            FoodCategoryModel(R.drawable.ic_image_burger, "Burger Bistro", "Rose garden", "$40",""),
            FoodCategoryModel(R.drawable.burger_1, "Smokin' Burger", "Cafenio Restaurant", "$25",""),
            FoodCategoryModel(R.drawable.burger_3, "Buffalo Burgers", "Kaji Firm Kitchen", "$60",""),
            FoodCategoryModel(R.drawable.burger_4, "Bullseye Burgers", "Kabab restaurant", "$75","")
        )

        val restrount = listOf<RestaurantModel>(
            RestaurantModel(R.drawable.ic_restorant_1, "Stumari", 4.6, 20, 6.0),
            RestaurantModel(R.drawable.res_2, "Burger House", 4.6, 20, null),
        )

        state.update {
            it.copy(burgers = food, restaurants = restrount)
        }
    }
}
