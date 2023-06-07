package com.smakolyk.ua.components.category.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.R
import com.smakolyk.ua.ui.theme.RoundColor

@Composable
fun FoodTitleComponent(
    modifier: Modifier = Modifier,
    categoryKey: String,
    onSearchAction: () -> Unit = {},
    onBackAction: () -> Unit = {},
    onFilterAction: () -> Unit = {}
) {
    ConstraintLayout(modifier = modifier, constraintSet = getConstraintSet()) {
        Image(
            modifier = Modifier
                .layoutId(FoodTitleComponentConstraints.SEARCH)
                .clip(CircleShape)
                .clickable { onSearchAction.invoke() },
            painter = painterResource(id = R.drawable.ic_rounded_search),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .layoutId(FoodTitleComponentConstraints.FILTER)
                .clip(CircleShape)
                .clickable { onFilterAction.invoke() },
            painter = painterResource(id = R.drawable.ic_filter),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .layoutId(FoodTitleComponentConstraints.BACK)
                .clip(CircleShape)
                .clickable { onBackAction.invoke() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = RoundColor,
                    shape = RoundedCornerShape(50)
                )
                .clip(RoundedCornerShape(50))
                .padding(vertical = 14.dp, horizontal = 20.dp)
                .layoutId(FoodTitleComponentConstraints.CATEGORY),
            text = categoryKey,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

private enum class FoodTitleComponentConstraints {
    BACK,
    CATEGORY,
    SEARCH,
    FILTER
}

private fun getConstraintSet() = ConstraintSet {
    val back = createRefFor(FoodTitleComponentConstraints.BACK)
    val category = createRefFor(FoodTitleComponentConstraints.CATEGORY)
    val search = createRefFor(FoodTitleComponentConstraints.SEARCH)
    val filter = createRefFor(FoodTitleComponentConstraints.FILTER)

    createHorizontalChain(category, search, chainStyle = ChainStyle.Spread)

    constrain(back) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
        end.linkTo(category.start, 12.dp)
    }

    constrain(category) {
        top.linkTo(parent.top)
        start.linkTo(back.end)
        bottom.linkTo(parent.bottom)
        end.linkTo(search.start)
    }

    constrain(search) {
        top.linkTo(parent.top)
        start.linkTo(category.end)
        bottom.linkTo(parent.bottom)
        end.linkTo(filter.start)
    }
    constrain(filter) {
        top.linkTo(parent.top)
        start.linkTo(search.end)
        bottom.linkTo(parent.bottom)
        end.linkTo(parent.end)
    }
}

@Preview(showBackground = true)
@Composable
fun FoodTitleComponentPreview() {
    FoodTitleComponent(categoryKey = "Burger")
}
