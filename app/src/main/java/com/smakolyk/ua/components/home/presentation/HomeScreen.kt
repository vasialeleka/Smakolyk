package com.smakolyk.ua.components.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smakolyk.ua.components.home.components.DeliveryTopBar
import com.smakolyk.ua.components.home.components.KeyWordsComponent
import com.smakolyk.ua.components.home.components.PopularFoodItemComponent
import com.smakolyk.ua.components.home.components.SearchComponent
import com.smakolyk.ua.components.home.components.category.CategoryComponent
import com.smakolyk.ua.components.home.components.category.HomeCategoryComponent
import com.smakolyk.ua.components.home.components.category.RestaurantItemComponent
import com.smakolyk.ua.components.home.components.category.RestaurantShortItemComponent
import com.smakolyk.ua.components.home.model.CategoryModel
import com.smakolyk.ua.components.home.model.PopularFoodModel
import com.smakolyk.ua.components.home.model.RestaurantModel
import com.smakolyk.ua.components.home.vm.HomeVm
import com.smakolyk.ua.navigation.Screen
import com.smakolyk.ua.ui.theme.Sen
import com.smakolyk.ua.ui.theme.SenBold
import com.smakolyk.ua.ui.theme.WelcomeTextColor

@Composable
fun HomeScreen(navController: NavController, vm: HomeVm = hiltViewModel()) {
    val viewState = vm.state.collectAsState()

    LazyColumn() {
        item {
            DeliveryTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                deliveryAddress = viewState.value.deliveryAddress,
                amount = viewState.value.binAmount,
                onBinClicked = {
                    navController.navigate(Screen.Card.route)
                }
            )
        }
        //  if (viewState.value.search.isEmpty()) {
        item {
            val welcomeMessage =
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontFamily = Sen,
                            fontWeight = MaterialTheme.typography.h6.fontWeight,
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            color = WelcomeTextColor
                        )
                    ) { append("Hey " + viewState.value.name + ",") }

                    withStyle(
                        SpanStyle(
                            fontFamily = SenBold,
                            fontWeight = MaterialTheme.typography.h6.fontWeight,
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            color = WelcomeTextColor
                        )
                    ) {
                        append("Good Afternoon!")
                    }
                }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp),
                text = welcomeMessage
            )
        }
        //  }

        item {
            SearchComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 24.dp),
                text = viewState.value.search,
                hint = "Search dishes, restaurants",
                onTextChanged = { vm.searchAction(it) }
            )
        }
        if (viewState.value.search.isEmpty()) {
            item {
                with(viewState.value) {
                    HomeGeneralComponent(
                        categories,
                        selectedCategory,
                        restaurants
                    ) { vm.onCategorySelected(it) }
                }
            }
        } else {
            item {
                with(viewState.value) {
                    SearchedComponent(
                        keyWords,
                        restaurants,
                        popular
                    ) {
                        navController.navigate(Screen.Category.route)
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchedComponent(
    keyWords: List<String>,
    restaurants: List<RestaurantModel>?,
    popularFood: List<PopularFoodModel>,
    onKeyWordClicked: () -> Unit
) {
    KeyWordsComponent(
        titleModifier = Modifier.padding(start = 24.dp),
        keyWords = keyWords,
        listPaddingValues = PaddingValues(horizontal = 24.dp),
        onKeyClickedAction = { onKeyWordClicked.invoke() }
    )
    restaurants?.let {
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            text = "Suggested Restaurants",
            style = MaterialTheme.typography.subtitle1
        )

        it.forEach {
            RestaurantShortItemComponent(
                modifier = Modifier.padding(horizontal = 24.dp),
                restaurantModel = it
            )
        }
    }

    Text(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
        text = "Popular Fast food",
        style = MaterialTheme.typography.subtitle1
    )

    for ((i, j) in popularFood.chunked(2)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            PopularFoodItemComponent(
                modifier = Modifier.weight(1f),
                popularFoodModel = i
            )

            PopularFoodItemComponent(
                modifier = Modifier.weight(1f),
                popularFoodModel = j
            )
        }
    }
}

@Composable
private fun HomeGeneralComponent(
    categories: List<CategoryModel>,
    selectedCategory: CategoryModel?,
    restaurants: List<RestaurantModel>?,
    onCategoryChanged: (CategoryModel) -> Unit = {}
) {
    HomeCategoryComponent(
        titleModifier = Modifier.padding(start = 24.dp),
        infoModifier = Modifier.padding(end = 24.dp),
        description = "All Categories"
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                categories
            ) { item ->
                CategoryComponent(
                    modifier = Modifier,
                    category = item,
                    isChecked = item == selectedCategory
                ) {
                    onCategoryChanged.invoke(it)
                }
            }
        }
    }

    HomeCategoryComponent(
        titleModifier = Modifier.padding(start = 24.dp, top = 24.dp),
        infoModifier = Modifier.padding(end = 24.dp, top = 24.dp),
        description = "Open Restaurants"
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            restaurants?.forEach {
                RestaurantItemComponent(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    restaurantModel = it
                )
            }
        }
    }
}
