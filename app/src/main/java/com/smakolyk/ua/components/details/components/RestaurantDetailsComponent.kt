package com.smakolyk.ua.components.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.ui.theme.RoundColor

@Composable
fun RestaurantDetailsComponent(modifier: Modifier = Modifier, restaurantTitle: String) {
    Row(
        modifier = modifier.border(
            width = 1.dp,
            color = RoundColor,
            shape = RoundedCornerShape(50)
        )
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = restaurantTitle,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview
@Composable
fun RestaurantDetailsComponentPreview() {
    RestaurantDetailsComponent(
        restaurantTitle = "Uttora Coffe House"
    )
}
