package com.example.step_mobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BlueDark = Color(0xFF1B56ED);
private val Blue = Color(red = 85, green = 184, blue = 255);
private val lightBlue = Color(0xFFBAE2FF)
private val White = Color(0xFFFFFFFF)

private val DarkColorPalette = darkColors(
    primary = BlueDark,
    primaryVariant = Blue,
    secondary = lightBlue
)

private val LightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = BlueDark,
    secondary = lightBlue,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val BottomNavPalette = lightColors(
    primary = White,
    primaryVariant = BlueDark,
    secondary = lightBlue,
    onPrimary = BlueDark
)

@Composable
fun BottomNavigationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        BottomNavPalette
    }

    MaterialTheme(
        colors = colors,
        typography = NavbarTypography,
        shapes = Shapes,
        content = content,
    )
}

@Composable
fun StepmobileTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}