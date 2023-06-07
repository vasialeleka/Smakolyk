package com.smakolyk.ua.components.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.smakolyk.ua.components.home.model.PopularFoodModel
import com.smakolyk.ua.ui.theme.DescriptionColor

@Composable
fun PopularFoodItemComponent(modifier: Modifier = Modifier, popularFoodModel: PopularFoodModel) {
    Card(
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        elevation = 8.dp
    ) {
        ConstraintLayout(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)),
            constraintSet = getConstraintSet()
        ) {
            Image(
                modifier = Modifier
                    .layoutId(PopularFoodItemConstraints.IMAGE)
                    .padding(8.dp)
                    .aspectRatio(1.5f)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painterResource(id = popularFoodModel.imageRes),
                contentDescription = popularFoodModel.title,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = Modifier
                    .layoutId(PopularFoodItemConstraints.TITLE)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                text = popularFoodModel.title,
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier
                    .layoutId(PopularFoodItemConstraints.SUBTITILE)
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                text = popularFoodModel.restaurant,
                style = MaterialTheme.typography.subtitle2,
                color = DescriptionColor
            )
        }
    }
}

private enum class PopularFoodItemConstraints {
    IMAGE,
    TITLE,
    SUBTITILE
}

private fun getConstraintSet() = ConstraintSet {
    val title = createRefFor(PopularFoodItemConstraints.TITLE)
    val image = createRefFor(PopularFoodItemConstraints.IMAGE)
    val subtitle = createRefFor(PopularFoodItemConstraints.SUBTITILE)

    constrain(image) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }

    constrain(title) {
        top.linkTo(image.bottom, 8.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(subtitle.top)
    }

    constrain(subtitle) {
        top.linkTo(title.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom, 12.dp)
    }
}

@Preview(showBackground = true)
@Composable
private
fun PopularFoodItemComponentPreview() {
    val first =
        PopularFoodModel(R.drawable.ic_restorant_1, "European Pizza", "Uttora Coffe House")
    val second =
        PopularFoodModel(R.drawable.ic_restorant_1, "European Pizza", "Uttora Coffe House")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PopularFoodItemComponent(modifier = Modifier.weight(1f), popularFoodModel = first)

        PopularFoodItemComponent(modifier = Modifier.weight(1f), popularFoodModel = second)
    }
}
