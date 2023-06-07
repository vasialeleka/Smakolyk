package com.smakolyk.ua.components.cart.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.smakolyk.ua.ui.theme.A0A5BA
import com.smakolyk.ua.ui.theme.EditTextBackgroundColor
import com.smakolyk.ua.ui.theme.EditTextHintColor
import com.smakolyk.ua.ui.theme.Main200

@Composable
fun FooterBinComponent(
    modifier: Modifier = Modifier,
    totalPrice: Int
) {
    val address = remember {
        mutableStateOf("Cherkasy, Nuzhnya Gorova 9")
    }
    ConstraintLayout(
        modifier = modifier
            .clip(RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
            .background(
                Color.White
            ),
        constraintSet = getConstraintSet()
    ) {
        Text(
            modifier = Modifier.layoutId(FooterBinConstraints.TITLE),
            text = "DELIVERY ADDRESS",
            color = A0A5BA,
            style = MaterialTheme.typography.subtitle2
        )

        Row(
            modifier = Modifier.layoutId(FooterBinConstraints.ADDRESS)
                .clip(RoundedCornerShape(8.dp)).background(EditTextBackgroundColor)
        ) {
            TextField(
                value = address.value ?: "",
                singleLine = true,
                onValueChange = { address.value = it },
                textStyle = MaterialTheme.typography.subtitle1,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically),
                        text = "Enter Address",
                        color = EditTextHintColor
                    )
                }
            )
        }

        Text(
            modifier = Modifier.layoutId(FooterBinConstraints.TOTAL),
            text = "TOTAL:  ",
            color = A0A5BA,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            modifier = Modifier.layoutId(FooterBinConstraints.PRICE),
            text = "$$totalPrice",
            style = MaterialTheme.typography.h6,
            fontSize = 30.sp
        )

        Text(
            modifier = Modifier.layoutId(FooterBinConstraints.BREACKDOWN),
            text = "Breakdown >",
            style = MaterialTheme.typography.subtitle2,
            color = Color.Black
        )

        Button(
            modifier = Modifier.fillMaxWidth().layoutId(FooterBinConstraints.BUTTON),
            colors = ButtonDefaults.buttonColors(backgroundColor = Main200),
            shape = RoundedCornerShape(12.dp),
            onClick = { }
        ) {
            Text(text = "FILTER", color = Color.Black)
        }
    }
}

private enum class FooterBinConstraints {
    TITLE,
    ADDRESS,
    TOTAL,
    PRICE,
    BUTTON,
    BREACKDOWN,
    EDIT
}

private fun getConstraintSet() = ConstraintSet {
    val title = createRefFor(FooterBinConstraints.TITLE)
    val address = createRefFor(FooterBinConstraints.ADDRESS)
    val total = createRefFor(FooterBinConstraints.TOTAL)
    val price = createRefFor(FooterBinConstraints.PRICE)
    val button = createRefFor(FooterBinConstraints.BUTTON)
    val breakdown = createRefFor(FooterBinConstraints.BREACKDOWN)
    val edit = createRefFor(FooterBinConstraints.EDIT)

    constrain(title) {
        top.linkTo(parent.top, 24.dp)
        start.linkTo(parent.start, 24.dp)
    }

    constrain(edit) {
        top.linkTo(title.bottom)
        bottom.linkTo(title.bottom)
        end.linkTo(parent.end, 24.dp)
    }

    constrain(address) {
        top.linkTo(title.bottom, 8.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.fillToConstraints
    }

    constrain(total) {
        start.linkTo(total.start, 24.dp)
        start.linkTo(address.start)
        top.linkTo(address.bottom, 36.dp)
    }

    constrain(price) {
        start.linkTo(total.end)
        top.linkTo(total.top)
        bottom.linkTo(total.bottom)
    }

    constrain(breakdown) {
        top.linkTo(total.top)
        bottom.linkTo(total.bottom)
        end.linkTo(parent.end, 24.dp)
    }

    constrain(button) {
        top.linkTo(price.bottom, 32.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        bottom.linkTo(parent.bottom, 32.dp)
        width = Dimension.fillToConstraints
    }
}

@Preview
@Composable
fun FooterBinComponentPreview() {
    FooterBinComponent(modifier = Modifier.fillMaxWidth(), 96)
}
