package com.smakolyk.ua.components.cart.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.R

@Composable
fun TopCardComponent(modifier: Modifier = Modifier, onBackclicked: () -> Unit = {}) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier.clip(CircleShape).clickable {
                onBackclicked.invoke()
            },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null
        )

        Text(modifier = Modifier.padding(start = 16.dp), text = "Card", color = Color.White)
    }
}

@Preview
@Composable
fun TopCardComponentPreview() {
    TopCardComponent()
}
