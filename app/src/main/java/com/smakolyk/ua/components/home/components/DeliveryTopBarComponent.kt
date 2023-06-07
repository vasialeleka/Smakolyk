package com.smakolyk.ua.components.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.smakolyk.ua.R
import com.smakolyk.ua.ui.theme.Main200
import com.smakolyk.ua.ui.theme.TextColorGray

@Composable
fun DeliveryTopBar(
    modifier: Modifier,
    deliveryAddress: String?,
    amount: Int = 0,
    onMenuClick: () -> Unit = {},
    onAddressClicked: () -> Unit = {},
    onBinClicked: () -> Unit = {}
) {
    ConstraintLayout(modifier = modifier, constraintSet = getConstraintSet()) {
        Image(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .layoutId(Constraints.MENU_ICON)
                .clip(CircleShape)
                .background(Main200)
                .clickable { onMenuClick.invoke() },
            painter = painterResource(id = R.drawable.ic_menu_1),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.layoutId(Constraints.DELIVERY_TO),
            text = "Deliver to".uppercase(),
            style = MaterialTheme.typography.h5,
            color = Main200
        )

        deliveryAddress?.let {
            Text(
                modifier = Modifier
                    .layoutId(Constraints.DELIVERY_ADDRESS)
                    .clickable { onAddressClicked.invoke() },
                text = deliveryAddress,
                style = MaterialTheme.typography.subtitle2,
                color = TextColorGray
            )

            Image(
                modifier = Modifier
                    .layoutId(Constraints.DROP_DOWN)
                    .clickable { onAddressClicked.invoke() },
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = null
            )
        }

        Box(modifier = Modifier.layoutId(Constraints.BIN)) {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .layoutId(Constraints.BIN)
                    .clip(CircleShape)
                    .background(Main200)
                    .clickable { onBinClicked.invoke() },
                painter = painterResource(id = R.drawable.ic_menu_1),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            if (amount > 0) {
                Text(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp, end = 12.dp)
                        .drawBehind {
                            drawCircle(
                                color = Main200,
                                radius = this.size.minDimension
                            )
                        }.padding(1.dp),

                    style = MaterialTheme.typography.body2,
                    text = amount.toString()
                )
            }
        }
    }
}

private enum class Constraints {
    MENU_ICON,
    DELIVERY_TO,
    DELIVERY_ADDRESS,
    DROP_DOWN,
    BIN
}

private fun getConstraintSet() = ConstraintSet {
    val menuIcon = createRefFor(Constraints.MENU_ICON)
    val deliveryToText = createRefFor(Constraints.DELIVERY_TO)
    val deliveryAddress = createRefFor(Constraints.DELIVERY_ADDRESS)
    val dropDown = createRefFor(Constraints.DROP_DOWN)
    val binBox = createRefFor(Constraints.BIN)

    createVerticalChain(deliveryToText, deliveryAddress, chainStyle = ChainStyle.Packed)

    constrain(menuIcon) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        end.linkTo(deliveryToText.start)
    }

    constrain(deliveryToText) {
        top.linkTo(parent.top)
        bottom.linkTo(deliveryAddress.top)
        start.linkTo(menuIcon.end, 16.dp)
    }

    constrain(deliveryAddress) {
        start.linkTo(deliveryToText.start)
        top.linkTo(deliveryToText.bottom, 4.dp)
        bottom.linkTo(parent.bottom)
    }

    constrain(dropDown) {
        start.linkTo(deliveryAddress.end, 8.dp)
        top.linkTo(deliveryAddress.top)
        bottom.linkTo(deliveryAddress.bottom)
    }
    constrain(binBox) {
        end.linkTo(parent.end)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
    }
}

@Preview(showBackground = true)
@Composable
fun DeliveryTopBarPreview() {
    Column() {
        DeliveryTopBar(Modifier.fillMaxWidth(), "Halal Lab Office", 1)

        DeliveryTopBar(Modifier.fillMaxWidth(), "Halal Lab Office", 0)

        DeliveryTopBar(Modifier.fillMaxWidth(), null, 0)
    }
}
