package com.smakolyk.ua.components.category.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.smakolyk.ua.components.category.model.FoodCategoryModel
import com.smakolyk.ua.ui.theme.DescriptionColor

@Composable
fun FoodCategoryItemComponent(
    modifier: Modifier = Modifier,
    foodCategory: FoodCategoryModel,
    onAddClickedAction: (FoodCategoryModel) -> Unit = {}
) {
    Card(
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        elevation = 8.dp
    ) {
        ConstraintLayout(constraintSet = getConstraintSet()) {
            Image(
                modifier = Modifier.layoutId(FoodCategoryItemConstraint.IMAGE)
                    .padding(8.dp)
                    .aspectRatio(1.4f)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = foodCategory.imageRes),
                contentDescription = foodCategory.title,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .layoutId(FoodCategoryItemConstraint.TITLE)
                    .fillMaxWidth()
                    .padding(8.dp),
                text = foodCategory.title
            )

            Text(
                modifier = Modifier
                    .layoutId(FoodCategoryItemConstraint.DESCRIPTION)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = foodCategory.restaurant,
                style = MaterialTheme.typography.subtitle1,
                color = DescriptionColor
            )

            Text(
                modifier = Modifier
                    .layoutId(FoodCategoryItemConstraint.PRICE)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = foodCategory.price
            )

            Image(
                modifier = Modifier
                    .layoutId(FoodCategoryItemConstraint.ADD)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .clickable { onAddClickedAction.invoke(foodCategory) },
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null
            )
        }
    }
}

private enum class FoodCategoryItemConstraint {
    IMAGE,
    TITLE,
    DESCRIPTION,
    PRICE,
    ADD
}

private fun getConstraintSet() = ConstraintSet() {
    val image = createRefFor(FoodCategoryItemConstraint.IMAGE)
    val title = createRefFor(FoodCategoryItemConstraint.TITLE)
    val description = createRefFor(FoodCategoryItemConstraint.DESCRIPTION)
    val price = createRefFor(FoodCategoryItemConstraint.PRICE)
    val add = createRefFor(FoodCategoryItemConstraint.ADD)

    constrain(image) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(title) {
        top.linkTo(image.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
    constrain(description) {
        top.linkTo(title.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(price) {
        top.linkTo(description.bottom)
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
    }

    constrain(add) {
        top.linkTo(description.bottom)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
}

@Preview(showBackground = true)
@Composable
fun FoodCategoryItemComponentPreview() {
    val food = FoodCategoryModel(R.drawable.ic_image_burger, "Burger Bistro", "Rose garden", "$40", description = "")
    FoodCategoryItemComponent(foodCategory = food)
}
