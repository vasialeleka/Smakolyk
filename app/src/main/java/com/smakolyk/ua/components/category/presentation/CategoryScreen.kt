package com.smakolyk.ua.components.category.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smakolyk.ua.components.category.components.FoodCategoryItemComponent
import com.smakolyk.ua.components.category.components.FoodTitleComponent
import com.smakolyk.ua.components.category.vm.CategoryVm
import com.smakolyk.ua.components.home.components.category.RestaurantItemComponent
import com.smakolyk.ua.navigation.Screen

@Composable
fun CategoryScreen(navController: NavController, vm: CategoryVm = hiltViewModel()) {
    val viewState = vm.state.collectAsState()
    var popupControl = remember { mutableStateOf(false) }

    if (popupControl.value) {
        Popup(alignment = Alignment.Center, onDismissRequest = { popupControl.value = false }) {
            FilterPopUp(modifier = Modifier.padding(12.dp)) { popupControl.value = false }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            FoodTitleComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                categoryKey = viewState.value.category,
                onBackAction = { navController.popBackStack() },
                onFilterAction = { popupControl.value = true }
            )
        }

        item {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = "Popular Burgers",
                style = MaterialTheme.typography.subtitle1
            )
        }

        for ((i, j) in viewState.value.burgers.chunked(2)) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    FoodCategoryItemComponent(
                        modifier = Modifier.weight(1f).clickable {
                            navController.navigate(Screen.Details.route)
                        },
                        foodCategory = i
                    )
                    FoodCategoryItemComponent(
                        modifier = Modifier.weight(1f).clickable {
                            navController.navigate(Screen.Details.route)
                        },
                        foodCategory = j
                    )
                }
            }
        }

        item {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 12.dp, top = 24.dp),
                text = "Open Restaurants",
                style = MaterialTheme.typography.subtitle1
            )
        }

        for (i in viewState.value.restaurants) {
            item {
                RestaurantItemComponent(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    restaurantModel = i
                )
            }
        }
    }
}
