package com.smakolyk.ua.components.cart.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.R
import com.smakolyk.ua.components.cart.components.CardFoodModel
import com.smakolyk.ua.components.details.components.CounterComponent

@Composable
fun CardFoodComponent(
    modifier: Modifier = Modifier,
    foodModel: CardFoodModel,
    amount: Int,
    onPlusClicked: () -> Unit = {},
    onMinusClicked: () -> Unit = {}
) {
    ConstraintLayout(modifier = modifier, constraintSet = getConstraintSet()) {
        Image(
            modifier = Modifier
                .width(150.dp)
                .aspectRatio(1.5f)
                .clip(RoundedCornerShape(12.dp))
                .layoutId(CardFoodConstraints.IMAGE),
            painter = painterResource(id = foodModel.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.layoutId(CardFoodConstraints.TITLE),
            text = foodModel.name,
            color = Color.White,
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            modifier = Modifier.layoutId(CardFoodConstraints.PRICE),
            text = "$${foodModel.price}",
            color = Color.White,
            style = MaterialTheme.typography.h5
        )

        Text(
            modifier = Modifier.layoutId(CardFoodConstraints.SIZE),
            text = foodModel.size,
            color = Color.White,
            style = MaterialTheme.typography.subtitle1
        )

        CounterComponent(
            modifier = Modifier.layoutId(CardFoodConstraints.COUNT),
            count = amount,
            backgrountColor = Color.Transparent,
            onPlusClicked = { onPlusClicked.invoke() },
            onMinusClicked = { onMinusClicked.invoke() }
        )
    }
}

private enum class CardFoodConstraints {
    IMAGE,
    TITLE,
    PRICE,
    COUNT,
    SIZE
}

private fun getConstraintSet() = ConstraintSet {
    val image = createRefFor(CardFoodConstraints.IMAGE)
    val title = createRefFor(CardFoodConstraints.TITLE)
    val price = createRefFor(CardFoodConstraints.PRICE)
    val count = createRefFor(CardFoodConstraints.COUNT)
    val size = createRefFor(CardFoodConstraints.SIZE)

    constrain(image) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
    }

    constrain(title) {
        top.linkTo(image.top)
        start.linkTo(image.end, 12.dp)
        bottom.linkTo(price.top)
    }

    constrain(price) {
        top.linkTo(title.bottom, 10.dp)
        start.linkTo(title.start)
        bottom.linkTo(size.top)
    }

    constrain(size) {
        top.linkTo(price.bottom, 10.dp)
        start.linkTo(title.start)
        bottom.linkTo(image.bottom)
    }

    constrain(count) {
        bottom.linkTo(image.bottom)
        end.linkTo(parent.end)
    }
}

@Preview
@Composable
fun CardFoodComponentPreview() {
    val foodModel = CardFoodModel(
        imageRes = R.drawable.ic_pizza,
        name = "Pizza Calzone European",
        amount = 1,
        price = 32,
        size = "14\""
    )
    CardFoodComponent(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black), foodModel = foodModel,1)
}
