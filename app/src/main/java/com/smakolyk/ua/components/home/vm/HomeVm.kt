package com.smakolyk.ua.components.home.vm

import androidx.lifecycle.ViewModel
import com.smakolyk.ua.R
import com.smakolyk.ua.components.home.model.CategoryModel
import com.smakolyk.ua.components.home.model.PopularFoodModel
import com.smakolyk.ua.components.home.model.RestaurantModel
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

        val listCategories = listOf<CategoryModel>(
            CategoryModel(R.drawable.ic_all_1, "All"),
            CategoryModel(R.drawable.pizza_1, "Pizza"),
            CategoryModel(R.drawable.ic_all, "Hot Dog"),
            CategoryModel(R.drawable.ic_image_burger, "Burger"),
            CategoryModel(R.drawable.ic_image_burger, "Cake"),
        )

        val restrount = listOf<RestaurantModel>(
            RestaurantModel(R.drawable.ic_restorant_1, "Stumari", 4.6, 20, 6.0),
            RestaurantModel(R.drawable.res_2, "Burger House", 4.6, 20, null),
        )

        val keys = listOf("Burger", "Pizza", "Hot Dogs", "Sandwich", "Pasta")

        val list = listOf(
            PopularFoodModel(R.drawable.pizza_1, "European Pizza", "1+1"),
            PopularFoodModel(R.drawable.pizza_2, "Ukrainian Pizza", "Pizza House"),
            PopularFoodModel(R.drawable.burger_1, "Burger Bistro", "McDonalds"),
            PopularFoodModel(R.drawable.burger_3, "Burger Buffalo", "McDonalds")
       )

        state.update {
            it.copy(
                name = "Asya",
                deliveryAddress = "Halal Lab office",
                binAmount = 2,
                categories = listCategories,
                restaurants = restrount,
                keyWords = keys,
                popular = list
            )
        }
    }

    fun searchAction(search: String) { // Todo actionDispacher
        state.update { it.copy(search = search) }
    }

    fun onCategorySelected(categoryModel: CategoryModel) {
        state.update { it.copy(selectedCategory = categoryModel) }
    }
}
