package com.smakolyk.ua.components.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.ui.theme.ColorAmount

@Composable
fun CountPriceComponent(
    modifier: Modifier = Modifier,
    amountInt: Int,
    priceInt: Int,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier.clip(
            RoundedCornerShape(
                topStartPercent = 50,
                topEndPercent = 50
            )
        ).background(ColorAmount),
        constraintSet = getConstraintSer()
    ) {
        Text(
            modifier = Modifier.layoutId(CountPriceConstraints.TOTAL_AMOUNT),
            text = "$$priceInt",
            style = MaterialTheme.typography.h6,
            fontSize = 28.sp
        )

        CounterComponent(
            modifier = Modifier.layoutId(CountPriceConstraints.COUNTER),
            count = amountInt,
            onPlusClicked = { onPlusClicked.invoke() },
            onMinusClicked = { onMinusClicked.invoke() }
        )
    }
}

private enum class CountPriceConstraints {
    TOTAL_AMOUNT,
    COUNTER
}

private fun getConstraintSer() = ConstraintSet {
    val amount = createRefFor(CountPriceConstraints.TOTAL_AMOUNT)
    val counter = createRefFor(CountPriceConstraints.COUNTER)

    constrain(amount) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start, 24.dp)
    }

    constrain(counter) {
        end.linkTo(parent.end, 24.dp)
        bottom.linkTo(parent.bottom, 16.dp)
        top.linkTo(parent.top, 20.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun CountPriceComponentPreview() {
    Column() {
        CountPriceComponent(modifier = Modifier.fillMaxWidth().padding(top = 30.dp), 1, 32, {}, {})
    }
}
