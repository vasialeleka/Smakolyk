package com.smakolyk.ua.components.details.model

import com.smakolyk.ua.R

data class IngredientModel(val imageRes: Int, val title: String) {
    companion object {
        fun getBaseIngredients(): List<IngredientModel> {
            return listOf(
                IngredientModel(R.drawable.ic_salt, "Salt"),
                IngredientModel(R.drawable.ic_chicken, "Chicken"),
                IngredientModel(R.drawable.ic_onion, "Onion"),
                IngredientModel(R.drawable.ic_garlic, "Garlic"),
                IngredientModel(R.drawable.ic_paper, "Paper")
            )
        }
    }
}
