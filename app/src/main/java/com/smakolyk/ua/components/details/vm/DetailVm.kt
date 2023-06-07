package com.smakolyk.ua.components.details.vm

import androidx.lifecycle.ViewModel
import com.smakolyk.ua.R
import com.smakolyk.ua.components.category.model.FoodCategoryModel
import com.smakolyk.ua.components.details.model.IngredientModel.Companion.getBaseIngredients
import com.smakolyk.ua.components.details.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailVm @Inject constructor() : ViewModel() {
    val state = MutableStateFlow(
        DetailState(
            FoodCategoryModel(
                R.drawable.ic_image_burger,
                "Burger Bistro",
                "Rose garden",
                "$40",
                description = "Prosciutto e funghi is a pizza variety that is topped with tomato sauce.",
                delivery = "20 Min",
                rating = "4.7",
                deliveryPrice = "Free",
                ingredients = getBaseIngredients()
            )
        )
    )

    fun onSizeClicked(size: String) {
        state.update { it.copy(selectedSize = size) }
    }

    fun onPlusCLicked() {
        state.update {
            it.copy(
                category = it.category.copy(
                    amount = it.category.amount + 1,
                    totalPrice = (it.category.amount + 1) * 40
                )
            )
        }
    }

    fun onMinusClicked() {
        if (state.value.category.amount > 1) {
            state.update {
                it.copy(
                    category = it.category.copy(
                        amount = it.category.amount - 1,
                        totalPrice = (it.category.amount -1) * 40
                    )
                )
            }
        } else {
            state.update {
                it.copy(
                    category = it.category.copy(
                        amount = 0,
                        totalPrice = 0
                    )
                )
            }
        }
    }
}
