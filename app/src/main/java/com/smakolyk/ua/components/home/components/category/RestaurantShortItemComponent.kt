package com.smakolyk.ua.components.home.components.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.R
import com.smakolyk.ua.components.home.components.ImageTextComponent
import com.smakolyk.ua.components.home.model.RestaurantModel
import com.smakolyk.ua.ui.theme.LinesColor
import com.smakolyk.ua.ui.theme.TextColorGray

@Composable
fun RestaurantShortItemComponent(modifier: Modifier = Modifier, restaurantModel: RestaurantModel) {
    ConstraintLayout(modifier = modifier, constraintSet = getConstraintSet()) {
        Image(
            modifier = Modifier
                .height(56.dp)
                .layoutId(RestaurantShortItemConstraints.ICON)
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(1f),
            painter = painterResource(id = restaurantModel.imageRes),
            contentDescription = restaurantModel.resTitle,
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.layoutId(RestaurantShortItemConstraints.TITLE),
            text = restaurantModel.resTitle,
            style = MaterialTheme.typography.subtitle1
        )

        ImageTextComponent(
            modifier = Modifier
                .layoutId(RestaurantShortItemConstraints.RATING)
                .padding(4.dp),
            imageRes = R.drawable.ic_rating,
            text = restaurantModel.rating.toString()
        )

        Box(
            modifier = Modifier
                .layoutId(RestaurantShortItemConstraints.LINE)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = LinesColor)
        )
    }
}

private enum class RestaurantShortItemConstraints {
    TITLE,
    ICON,
    RATING,
    LINE
}

private fun getConstraintSet() = ConstraintSet {
    val title = createRefFor(RestaurantShortItemConstraints.TITLE)
    val icon = createRefFor(RestaurantShortItemConstraints.ICON)
    val rating = createRefFor(RestaurantShortItemConstraints.RATING)
    val line = createRefFor(RestaurantShortItemConstraints.LINE)

    createVerticalChain(title, rating, chainStyle = ChainStyle.Packed)

    constrain(icon) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
    }

    constrain(title) {
        top.linkTo(parent.top)
        start.linkTo(icon.end, 10.dp)
        bottom.linkTo(rating.top)
    }

    constrain(rating) {
        top.linkTo(title.bottom)
        start.linkTo(icon.end, 10.dp)
        bottom.linkTo(line.bottom)
    }

    constrain(line) {
        top.linkTo(icon.bottom, 14.dp)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantShortItemComponentPreview() {
    val restaurantModel =
        RestaurantModel(R.drawable.ic_restorant_1, "Rose Garden Restaurant", 4.6, 20, null)
    RestaurantShortItemComponent(restaurantModel = restaurantModel)
}
