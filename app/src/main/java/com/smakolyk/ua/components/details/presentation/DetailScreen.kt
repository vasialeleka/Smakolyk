package com.smakolyk.ua.components.details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smakolyk.ua.R
import com.smakolyk.ua.components.details.components.CountPriceComponent
import com.smakolyk.ua.components.details.components.DetailsTopComponent
import com.smakolyk.ua.components.details.components.IngredientComponent
import com.smakolyk.ua.components.details.components.RestaurantDetailsComponent
import com.smakolyk.ua.components.details.components.SizeComponent
import com.smakolyk.ua.components.details.state.DetailState
import com.smakolyk.ua.components.details.vm.DetailVm
import com.smakolyk.ua.components.home.components.ImageTextComponent

@Composable
fun DetailScreen(navController: NavController, vm: DetailVm = hiltViewModel()) {
    val viewState = vm.state.collectAsState()

    ConstraintLayout(
        constraintSet = getConstraintSet(),
        modifier = Modifier.fillMaxSize()
    ) {
        LazyContent(viewState, vm, navController)
        CountPriceComponent(
            modifier = Modifier
                .layoutId(DetailConstraint.COUNTER)
                .fillMaxWidth(),
            amountInt = viewState.value.category.amount,
            priceInt = viewState.value.category.totalPrice,
            onMinusClicked = { vm.onMinusClicked() },
            onPlusClicked = { vm.onPlusCLicked() }
        )
    }
}

private enum class DetailConstraint {
    LAZY,
    COUNTER
}

private fun getConstraintSet() = ConstraintSet {
    val lazy = createRefFor(DetailConstraint.LAZY)
    val counter = createRefFor(DetailConstraint.COUNTER)

    constrain(lazy) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(counter.top)
        height = Dimension.fillToConstraints
    }

    constrain(counter) {
        top.linkTo(lazy.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

@Composable
private fun LazyContent(
    viewState: State<DetailState>,
    vm: DetailVm,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .layoutId(DetailConstraint.LAZY)

    ) {
        item {
            DetailsTopComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                navController.popBackStack()
            }
        }

        item {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .aspectRatio(1.4f)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = viewState.value.category.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        item {
            RestaurantDetailsComponent(
                modifier = Modifier.padding(horizontal = 24.dp),
                restaurantTitle = viewState.value.category.restaurant
            )
        }

        item {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                text = viewState.value.category.title,
                fontSize = 18.sp
            )
        }

        item {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                text = viewState.value.category.description,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 14.sp
            )
        }

        item {
            Row(modifier = Modifier.padding(horizontal = 24.dp)) {
                ImageTextComponent(
                    modifier = Modifier.padding(end = 8.dp, top = 8.dp),
                    imageRes = R.drawable.ic_delivery,
                    text = viewState.value.category.deliveryPrice
                )
                ImageTextComponent(
                    modifier = Modifier.padding(8.dp),
                    imageRes = R.drawable.clock,
                    text = viewState.value.category.delivery
                )
                ImageTextComponent(
                    modifier = Modifier.padding(8.dp),
                    imageRes = R.drawable.ic_rating,
                    text = viewState.value.category.rating
                )
            }
        }

        item {
            Row(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Ingridents Size:".uppercase())
                viewState.value.ingredientSizes.forEach {
                    SizeComponent(
                        modifier = Modifier.padding(4.dp),
                        size = it,
                        isChecked = it == vm.state.value.selectedSize
                    ) { vm.onSizeClicked(it) }
                }
            }
        }

        item {
            Row(modifier = Modifier.padding(24.dp)) {
                viewState.value.category.ingredients.forEach {
                    IngredientComponent(modifier = Modifier.weight(1f), ingredient = it)
                }
            }
        }
    }
}
