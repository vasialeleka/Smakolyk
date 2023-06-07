package com.smakolyk.ua.components.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.ui.theme.Main200

@Composable
fun KeyWordsComponent(
    layoutModifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    keyWords: List<String>,
    listPaddingValues: PaddingValues = PaddingValues(24.dp),
    onKeyClickedAction: (String) -> Unit = {}
) {
    Column(modifier = layoutModifier) {
        Text(
            modifier = titleModifier,
            text = "Recent Keywords",
            style = MaterialTheme.typography.subtitle1
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = listPaddingValues
        ) {
            items(keyWords) {
                Text(
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Main200,
                            shape = RoundedCornerShape(50)
                        )
                        .clip(RoundedCornerShape(50))
                        .clickable { onKeyClickedAction.invoke(it) }
                        .padding(vertical = 14.dp, horizontal = 20.dp),
                    text = it,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KeyWordsComponentPreview() {
    val keys = listOf("Burger", "Pizza", "Hot Dogs", "Sandwich", "Pasta")
    KeyWordsComponent(keyWords = keys)
}
