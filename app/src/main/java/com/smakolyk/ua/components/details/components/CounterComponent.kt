package com.smakolyk.ua.components.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import com.smakolyk.ua.ui.theme.ColorDark

@Composable
fun CounterComponent(
    modifier: Modifier = Modifier,
    count: Int,
    backgrountColor: Color = ColorDark,
    onPlusClicked: () -> Unit,
    onMinusClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(backgrountColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(12.dp)
                .clip(CircleShape)
                .clickable { onMinusClicked.invoke() },
            painter = painterResource(id = R.drawable.ic_minus),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 6.dp),
            text = count.toString(),
            style = MaterialTheme.typography.h6,
            color = Color.White
        )

        Image(
            modifier = Modifier
                .padding(12.dp)
                .clip(CircleShape)
                .clickable { onPlusClicked.invoke() },
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CounterComponentPreview() {
    CounterComponent(count = 1, onMinusClicked = {}, onPlusClicked = {})
}
