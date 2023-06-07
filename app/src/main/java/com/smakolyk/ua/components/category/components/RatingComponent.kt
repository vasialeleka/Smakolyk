package com.smakolyk.ua.components.category.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.R

@Composable
fun RatingComponent(
    modifier: Modifier = Modifier,
    amount: Int,
    amountSelected: Int = 0,
    onRatingClicked: (Int) -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(text = "Rating".uppercase())
        Row(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 0 until amount) {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onRatingClicked.invoke(i) },
                    painter = painterResource(id = if (i <= amountSelected) R.drawable.ic_star_selected else R.drawable.ic_star_not_selected),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingComponentPreview() {
    Column() {
        RatingComponent(modifier = Modifier.fillMaxWidth(), 5)
        RatingComponent(modifier = Modifier.fillMaxWidth(), 5, 3)
    }
}
