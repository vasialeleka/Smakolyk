package com.smakolyk.ua.components.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.R
import com.smakolyk.ua.ui.theme.EditTextBackgroundColor
import com.smakolyk.ua.ui.theme.EditTextHintColor

@Composable
fun SearchComponent(
    modifier: Modifier,
    text: String? = null,
    hint: String = "",
    onTextChanged: (String) -> Unit = {}
) {
    Row(modifier = modifier.clip(RoundedCornerShape(8.dp)).background(EditTextBackgroundColor)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text ?: "",
            singleLine = true,
            onValueChange = { onTextChanged.invoke(it) },
            textStyle = MaterialTheme.typography.h5,
            leadingIcon = {
                Image(
                    modifier = Modifier.padding(20.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically),
                    text = hint,
                    color = EditTextHintColor
                )
            }
        )
    }
}

@Preview
@Composable
fun SearchComponentPreview() {
    Column() {
        SearchComponent(modifier = Modifier.fillMaxWidth(), hint = "Search dishes, restaurants")
    }
}
