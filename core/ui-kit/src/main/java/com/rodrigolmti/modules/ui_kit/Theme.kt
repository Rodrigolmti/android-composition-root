package com.rodrigolmti.modules.ui_kit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColors(
    primary = Purple40,
    onPrimary = Color.White,
    secondary = Orange40,
    onSecondary = Color.White,
    error = Red40,
    onError = Color.White,
    background = DarkPurpleGray99,
    onBackground = DarkPurpleGray10,
    surface = DarkPurpleGray99,
    onSurface = DarkPurpleGray10,
)

val DarkColorScheme = darkColors(
    primary = Purple80,
    onPrimary = Purple20,
    secondary = Orange80,
    onSecondary = Orange20,
    error = Red80,
    onError = Red20,
    background = DarkPurpleGray10,
    onBackground = DarkPurpleGray90,
    surface = DarkPurpleGray10,
    onSurface = DarkPurpleGray90,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    MaterialTheme(
        colors = colors,
        content = content,
    )
}