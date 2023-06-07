package com.smakolyk.ua.components.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smakolyk.ua.components.details.model.IngredientModel
import com.smakolyk.ua.ui.theme.IngredientColor
import com.smakolyk.ua.ui.theme.TextColorGray

@Composable
fun IngredientComponent(modifier: Modifier = Modifier, ingredient: IngredientModel) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .background(IngredientColor)
                .padding(8.dp),
            painter = painterResource(id = ingredient.imageRes),
            contentDescription = ingredient.imageRes.toString()
        )

        Text(
            text = ingredient.title,
            style = MaterialTheme.typography.body1,
            color = TextColorGray,
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IngredientComponentPreview() {
    IngredientComponent(ingredient = IngredientModel.getBaseIngredients().first())
}
