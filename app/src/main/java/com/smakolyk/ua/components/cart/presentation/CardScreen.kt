package com.smakolyk.ua.components.cart.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smakolyk.ua.components.cart.model.CardFoodComponent
import com.smakolyk.ua.components.cart.model.FooterBinComponent
import com.smakolyk.ua.components.cart.model.TopCardComponent
import com.smakolyk.ua.components.cart.vm.CardVm
import com.smakolyk.ua.ui.theme.cardBackground

@Composable
fun CardScreen(navController: NavController, vm: CardVm = hiltViewModel()) {
    val viewStat = vm.state.collectAsState()
    ConstraintLayout(
        modifier = Modifier.fillMaxSize().background(cardBackground),
        constraintSet = getConstraintSet()
    ) {
        TopCardComponent(modifier = Modifier.layoutId(CardConstraints.TITLE)) { navController.popBackStack() }

        LazyColumn(modifier = Modifier.layoutId(CardConstraints.FOODCOLUMN)) {
            items(viewStat.value.foodModels) {
                CardFoodComponent(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 24.dp),
                    it,
                    amount = if (viewStat.value.foodModels.indexOf(it) == 0) viewStat.value.amount1 else viewStat.value.amount2,
                    onMinusClicked = { vm.onMinusClicked(viewStat.value.foodModels.indexOf(it)) },
                    onPlusClicked = { vm.onPlusClicked(viewStat.value.foodModels.indexOf(it)) }
                )
            }
        }

        FooterBinComponent(
            modifier = Modifier.layoutId(CardConstraints.FOOTER),
            totalPrice = viewStat.value.totalPrice
        )
    }
}

private enum class CardConstraints {
    TITLE,
    FOODCOLUMN,
    FOOTER
}

private fun getConstraintSet() = ConstraintSet {
    val title = createRefFor(CardConstraints.TITLE)
    val food = createRefFor(CardConstraints.FOODCOLUMN)
    val footer = createRefFor(CardConstraints.FOOTER)

    constrain(title) {
        top.linkTo(parent.top, 24.dp)
        start.linkTo(parent.start, 24.dp)
    }

    constrain(food) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(title.bottom)
        bottom.linkTo(footer.top)
        height = Dimension.fillToConstraints
        width = Dimension.fillToConstraints
    }

    constrain(footer) {
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        top.linkTo(food.bottom)
        bottom.linkTo(parent.bottom)
        width = Dimension.fillToConstraints
    }
}
