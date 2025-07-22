package com.groot.quotify.ui.theme


import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define your color schemes
private val DarkColorScheme = darkColorScheme(
    primary = jetBlack,
    secondary = platinum,
    tertiary = jetBlack,
    background = /* Your dark background */ jetBlack,
    onBackground = /* Your text color on dark background */ platinum,
    onSurface = /* Your text color on dark surface */ platinum,
)

private val LightColorScheme = lightColorScheme(
    primary = platinum,
    secondary = jetBlack,
    tertiary = Pink40,
    background = /* Your light background */ platinum,
    onBackground = /* Your text color on light background */ Black,
    onSurface = /* Your text color on light surface */ Black,
)

@Composable
fun QuotifyTheme(
    content: @Composable () -> Unit,
    isDarkTheme: MutableState<Boolean>
) {
    val colorScheme = if (isDarkTheme.value) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb() // Or your desired status bar color
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme.value
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Assuming you have Typography defined
        content = content
    )
}