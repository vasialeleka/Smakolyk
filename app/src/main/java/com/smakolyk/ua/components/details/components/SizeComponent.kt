package com.smakolyk.ua.components.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.ui.theme.Main200
import com.smakolyk.ua.ui.theme.RoundColor

@Composable
fun SizeComponent(
    modifier: Modifier = Modifier,
    size: String,
    isChecked: Boolean,
    onSizeClicked: (String) -> Unit = {}
) {
    Text(
        modifier = modifier
            .drawBehind {
                drawCircle(
                    if (isChecked) {
                        Main200
                    } else {
                        RoundColor
                    }
                )
            }
            .padding(8.dp)
            .clickable { onSizeClicked.invoke(size) }
            .clip(CircleShape),
        text = size,
        style = MaterialTheme.typography.h6
    )
}

@Preview(showBackground = true)
@Composable
fun SizeComponentPreview() {
    Column() {
        SizeComponent(modifier = Modifier, size = "18\"", isChecked = false)
        SizeComponent(modifier = Modifier, size = "18\"", isChecked = true)
    }
}

fun Modifier.badgeLayout() =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // based on the expectation of only one line of text
        val minPadding = placeable.height / 4

        val width = maxOf(placeable.width + minPadding, placeable.height)
        layout(width, placeable.height) {
            placeable.place((width - placeable.width) / 2, 0)
        }
    }