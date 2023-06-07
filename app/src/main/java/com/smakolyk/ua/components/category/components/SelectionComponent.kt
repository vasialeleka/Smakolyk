package com.smakolyk.ua.components.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.ui.theme.EditTextHintColor
import com.smakolyk.ua.ui.theme.Main200

@Composable
fun SelectionComponent(
    modifier: Modifier = Modifier,
    title: String,
    options: List<String>,
    selectedOption: String? = null,
    onOptionClicked: (String) -> Unit = { }
) {
    Column(modifier = modifier) {
        Text(modifier = Modifier.padding(horizontal = 24.dp), text = title.uppercase())

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp)
        ) {
            items(options) { text ->
                Text(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = if (text == selectedOption) Main200 else EditTextHintColor,
                            shape = RoundedCornerShape(50)
                        )
                        .clip(RoundedCornerShape(50))
                        .background(
                            if (text == selectedOption) {
                                Main200
                            } else {
                                Color.Transparent
                            }
                        )
                        .clickable { onOptionClicked.invoke(text) }
                        .padding(vertical = 14.dp, horizontal = 20.dp),
                    text = text,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectionComponentPreview() {
    Column() {
        SelectionComponent(title = "Offers", options = listOf("Delivery", "Delivery", "Online payment available", "Pick Up"))
        SelectionComponent(title = "Offers", options = listOf("Delivery", "Online payment available", "Pick Up"), selectedOption = "Delivery")
    }
}
