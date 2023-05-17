package com.smakolyk.ua.components.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smakolyk.ua.components.home.components.DeliveryTopBar
import com.smakolyk.ua.components.home.vm.HomeVm
import com.smakolyk.ua.ui.theme.Poppins
import com.smakolyk.ua.ui.theme.PoppinsBold
import com.smakolyk.ua.ui.theme.WelcomeTextColor

@Composable
fun HomeComponent(navController: NavController, vm: HomeVm = hiltViewModel()) {
    val viewState = vm.state.collectAsState()
    Column() {
        DeliveryTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            deliveryAddress = viewState.value.deliveryAddress,
            amount = viewState.value.binAmount
        )

        val welcomeMessage =
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontFamily = Poppins,
                        fontWeight = MaterialTheme.typography.h6.fontWeight,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        color = WelcomeTextColor
                    )
                ) { append("Hey " + viewState.value.name + ",") }

                withStyle(
                    SpanStyle(
                        fontFamily = PoppinsBold,
                        fontWeight = MaterialTheme.typography.h6.fontWeight,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        color = WelcomeTextColor
                    )
                ) {
                    append("Good Afternoon!")
                }
            }

        Text(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), text = welcomeMessage)
    }
}
