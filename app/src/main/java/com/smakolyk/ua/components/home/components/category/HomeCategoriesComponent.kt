package com.smakolyk.ua.components.home.components.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.R
import com.smakolyk.ua.components.home.model.CategoryModel

@Composable
fun HomeCategoryComponent(
    titleModifier: Modifier,
    infoModifier: Modifier,
    description: String,
    onSeeAllClicked: () -> Unit = {},
    content: @Composable () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth(), constraintSet = getConstraintSet()) {
        Text(
            modifier = titleModifier.layoutId(HomeCategoryConstraints.TITLE),
            text = description,
            style = MaterialTheme.typography.subtitle1
        )
        Box(modifier = Modifier.layoutId(HomeCategoryConstraints.CATEGORIES_LIST)) {
            content()
        }
        Row(
            modifier = infoModifier
                .layoutId(HomeCategoryConstraints.SEE_ALL_ROW)
                .clip(RoundedCornerShape(50))
                .clickable { onSeeAllClicked.invoke() }
        ) {
            Text(
                modifier = Modifier.align(CenterVertically).padding(start = 4.dp),
                text = "See all",
                style = MaterialTheme.typography.subtitle1
            )
            Image(
                modifier = Modifier
                    .height(12.dp)
                    .align(CenterVertically)
                    .padding(start = 12.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null
            )
        }
    }
}

private enum class HomeCategoryConstraints {
    TITLE,
    SEE_ALL_ROW,
    CATEGORIES_LIST
}

private fun getConstraintSet() = ConstraintSet {
    val title = createRefFor(HomeCategoryConstraints.TITLE)
    val categoryList = createRefFor(HomeCategoryConstraints.CATEGORIES_LIST)
    val seeAllRow = createRefFor(HomeCategoryConstraints.SEE_ALL_ROW)

    constrain(title) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        bottom.linkTo(categoryList.top)
    }

    constrain(categoryList) {
        top.linkTo(title.bottom, 24.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }

    constrain(seeAllRow) {
        top.linkTo(parent.top)
        end.linkTo(parent.end)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeCategoryComponentPreview() {
    val listCategories = listOf<CategoryModel>(
        CategoryModel(R.drawable.ic_image_burger, "Burger"),
        CategoryModel(R.drawable.ic_image_burger, "Burger"),
        CategoryModel(R.drawable.ic_image_burger, "Burger"),
        CategoryModel(R.drawable.ic_image_burger, "Burger"),
        CategoryModel(R.drawable.ic_image_burger, "Burger"),
        CategoryModel(R.drawable.ic_image_burger, "Burger")
    )
    Column {
        HomeCategoryComponent(
            titleModifier = Modifier,
            infoModifier = Modifier,
            onSeeAllClicked = { },
            description = "All categories"
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(4.dp)
            ) {
                items(
                    listCategories
                ) { item ->
                    CategoryComponent(
                        modifier = Modifier,
                        category = item,
                        isChecked = false
                    )
                }
            }
        }
    }
}
