package com.smakolyk.ua.components.details.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.R

@Composable
fun DetailsTopComponent(modifier: Modifier = Modifier, onBackClicked: () -> Unit = {}) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .padding(end = 16.dp)
                .clip(CircleShape)
                .clickable { onBackClicked.invoke() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null
        )

        Text(text = "Details")
    }
}

@Preview
@Composable
fun DetailsTopComponentPreview() {
    DetailsTopComponent()
}
