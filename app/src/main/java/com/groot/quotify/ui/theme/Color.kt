package com.groot.quotify.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.ShaderBrush

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val Blue0 = Color(0xFF4158D0)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val pink = Color(0xFFEEAECA)
val blue = Color(0xFF94BBE9)

val gradientStart = Color(0xFFe9b7ce)
val gradientMiddle = Color(0xFFd3f3f1)
val gradientEnd = Color(0xFFd3f3f1)
val largeRadialGradient = object : ShaderBrush() {
    override fun createShader(size: Size): androidx.compose.ui.graphics.Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(blue, pink),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}

val gradient = Brush.linearGradient(
    0.0f to Color(0xFF4158D0),
    0.46f to Color(0xFFC850C0),
    1.0f to Color(0xFFFFCC70),
    start = Offset.Zero,
    end = Offset.Infinite
)