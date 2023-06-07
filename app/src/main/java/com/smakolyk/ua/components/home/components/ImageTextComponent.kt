package com.smakolyk.ua.components.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.R

@Composable
fun ImageTextComponent(modifier: Modifier, imageRes: Int, text: String) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier.align(CenterVertically),
            painter = painterResource(id = imageRes),
            contentDescription = text
        )
        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = 8.dp),
            text = text,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageTextComponentPreview() {
    Column() {
        ImageTextComponent(modifier = Modifier.padding(8.dp), R.drawable.ic_delivery, "Free")
        ImageTextComponent(modifier = Modifier.padding(8.dp), R.drawable.clock, "20 min")
        ImageTextComponent(modifier = Modifier.padding(8.dp), R.drawable.ic_rating, "2.6")
    }
}
