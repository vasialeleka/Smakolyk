package com.smakolyk.ua.components.home.components.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.R
import com.smakolyk.ua.components.home.components.ImageTextComponent
import com.smakolyk.ua.components.home.model.RestaurantModel
import com.smakolyk.ua.ui.theme.DescriptionColor

@Composable
fun RestaurantItemComponent(modifier: Modifier, restaurantModel: RestaurantModel) {
    ConstraintLayout(modifier = modifier, constraintSet = getConstraintSet()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(RestaurantItemComponentConstraints.IMAGE)
                .aspectRatio(3f)
                .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(id = restaurantModel.imageRes),
            contentDescription = restaurantModel.resTitle,
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier.layoutId(RestaurantItemComponentConstraints.TITLE),
            text = restaurantModel.resTitle,
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            modifier = Modifier.layoutId(RestaurantItemComponentConstraints.CATEGORIES),
            text = restaurantModel.resTitle,
            style = MaterialTheme.typography.subtitle2,
            color = DescriptionColor
        )

        ImageTextComponent(
            modifier = Modifier.layoutId(RestaurantItemComponentConstraints.DELIVERY_TIME),
            imageRes = R.drawable.clock,
            text = restaurantModel.deliveryTime.toString() + " min"
        )

        ImageTextComponent(
            modifier = Modifier.layoutId(RestaurantItemComponentConstraints.DELIVERY_SUM),
            imageRes = R.drawable.ic_delivery,
            text = restaurantModel.deliveryPrice?.toString() ?: "Free"
        )

        ImageTextComponent(
            modifier = Modifier.layoutId(RestaurantItemComponentConstraints.RATING),
            imageRes = R.drawable.ic_rating,
            text = restaurantModel.rating.toString()
        )
    }
}

private enum class RestaurantItemComponentConstraints {
    IMAGE,
    TITLE,
    CATEGORIES,
    RATING,
    DELIVERY_SUM,
    DELIVERY_TIME
}

private fun getConstraintSet() = ConstraintSet {
    val image = createRefFor(RestaurantItemComponentConstraints.IMAGE)
    val title = createRefFor(RestaurantItemComponentConstraints.TITLE)
    val categories = createRefFor(RestaurantItemComponentConstraints.CATEGORIES)
    val rating = createRefFor(RestaurantItemComponentConstraints.RATING)
    val deliverySum = createRefFor(RestaurantItemComponentConstraints.DELIVERY_SUM)
    val deliveryTime = createRefFor(RestaurantItemComponentConstraints.DELIVERY_TIME)

    constrain(image) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(title) {
        top.linkTo(image.bottom, 8.dp)
        start.linkTo(parent.start)
        bottom.linkTo(categories.top)
    }

    constrain(categories) {
        top.linkTo(title.bottom, 4.dp)
        start.linkTo(parent.start)
        bottom.linkTo(rating.top)
    }

    constrain(rating) {
        top.linkTo(categories.bottom, 16.dp)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
    }

    constrain(deliverySum) {
        top.linkTo(categories.bottom, 16.dp)
        bottom.linkTo(parent.bottom)
        start.linkTo(rating.end, 16.dp)
    }

    constrain(deliveryTime) {
        top.linkTo(categories.bottom, 16.dp)
        bottom.linkTo(parent.bottom)
        start.linkTo(deliverySum.end, 16.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantItemComponentPreview() {
    Column {
        val restaurantModel =
            RestaurantModel(R.drawable.ic_restorant_1, "Rose Garden Restaurant", 4.6, 20, null)
        RestaurantItemComponent(modifier = Modifier.fillMaxWidth(), restaurantModel)
    }
}
