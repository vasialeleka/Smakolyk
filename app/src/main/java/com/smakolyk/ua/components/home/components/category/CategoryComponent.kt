package com.smakolyk.ua.components.home.components.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smakolyk.ua.R
import com.smakolyk.ua.components.home.model.CategoryModel
import com.smakolyk.ua.ui.theme.EditTextBackgroundColor
import com.smakolyk.ua.ui.theme.Main200

@Composable
fun CategoryComponent(
    modifier: Modifier,
    category: CategoryModel,
    isChecked: Boolean,
    onCategoryClick: (CategoryModel) -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(if (isChecked) Main200 else EditTextBackgroundColor)
            .clickable { onCategoryClick.invoke(category) }
    ) {
        Image(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(50)),
            painter = painterResource(id = category.imageResInt),
            contentDescription = category.title
        )

        Text(
            modifier = Modifier
                .align(CenterVertically)
                .padding(start = 4.dp, end = 24.dp),
            text = category.title,
            style = MaterialTheme.typography.h5
        )
    }
}

@Preview
@Composable
fun CategoryComponentPreview() {
    Column {
        CategoryComponent(
            Modifier.padding(10.dp),
            CategoryModel(R.drawable.ic_image_burger, "Burger"),
            true
        )
        CategoryComponent(
            Modifier.padding(10.dp),
            CategoryModel(R.drawable.ic_image_burger, "Burger"),
            false
        )
    }
}
