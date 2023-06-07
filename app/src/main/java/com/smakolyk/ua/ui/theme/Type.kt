package com.smakolyk.ua.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.smakolyk.ua.R

val Poppins = FontFamily(Font(R.font.poppins_black))
val Sen = FontFamily(Font(R.font.sen_regular))
val PoppinsBold = FontFamily(Font(R.font.poppins_bold))
val SenBold = FontFamily(Font(R.font.sen_bold))

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp
    ),
    body2 = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    h6 = TextStyle(
        fontFamily = Sen,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = SenBold,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Sen,
        fontSize = 18.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = Sen,
        fontSize = 14.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
