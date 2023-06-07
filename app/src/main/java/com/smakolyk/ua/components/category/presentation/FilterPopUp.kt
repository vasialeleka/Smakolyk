package com.smakolyk.ua.components.category.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.R
import com.smakolyk.ua.components.category.components.RatingComponent
import com.smakolyk.ua.components.category.components.SelectionComponent
import com.smakolyk.ua.ui.theme.Main200
import com.smakolyk.ua.ui.theme.background

@Composable
fun FilterPopUp(modifier: Modifier = Modifier, onCloseClicked: () -> Unit = {}) {
    val selectedOffer = remember { mutableStateOf("") }
    val selectedDelivery = remember { mutableStateOf("") }
    val selectedPricing = remember { mutableStateOf("") }
    val rating = remember { mutableStateOf(0) }

    Box(Modifier.fillMaxSize().background(background), contentAlignment = Alignment.Center) {
        Column(modifier.padding(12.dp).clip(RoundedCornerShape(24.dp)).background(Color.White)) {
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                constraintSet = getConstraintSet()
            ) {
                Text(
                    modifier = Modifier.layoutId(FilterPopUpConstraints.TITLE),
                    text = "Filter your search"
                )

                Image(
                    modifier = Modifier.clip(CircleShape).layoutId(FilterPopUpConstraints.CLOSE)
                        .clickable { onCloseClicked.invoke() },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null
                )
            }
            SelectionComponent(
                title = "Offers",
                options = listOf("Delivery", "Online payment available", "Pick Up"),
                selectedOption = selectedOffer.value,
                onOptionClicked = { selectedOffer.value = it }
            )
            SelectionComponent(
                title = "Deliver Time",
                options = listOf("10-25 min", "20 min", "25 min", "30 min"),
                selectedOption = selectedDelivery.value,
                onOptionClicked = { selectedDelivery.value = it }
            )
            SelectionComponent(
                title = "Pricing",
                options = listOf("$", "$$", "$$$"),
                selectedOption = selectedPricing.value,
                onOptionClicked = { selectedPricing.value = it }
            )

            RatingComponent(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                amount = 5,
                amountSelected = rating.value
            ) {
                rating.value = it
            }

            Button(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Main200),
                shape = RoundedCornerShape(12.dp),
                onClick = { onCloseClicked.invoke() }
            ) {
                Text(text = "FILTER", color = Color.Black)
            }
        }
    }
}

private enum class FilterPopUpConstraints {
    TITLE,
    CLOSE
}

private fun getConstraintSet() = ConstraintSet {
    val title = createRefFor(FilterPopUpConstraints.TITLE)
    val close = createRefFor(FilterPopUpConstraints.CLOSE)

    constrain(title) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
    }

    constrain(close) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        end.linkTo(parent.end)
    }
}

@Preview
@Composable
fun FilterPopUpPreview() {
    FilterPopUp()
}
